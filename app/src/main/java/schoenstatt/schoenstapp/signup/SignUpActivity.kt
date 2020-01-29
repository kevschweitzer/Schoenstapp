package schoenstatt.schoenstapp.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.blacktobacco.com.domain.Correct
import dev.blacktobacco.com.domain.EmailAlreadyInUseException
import dev.blacktobacco.com.domain.WeakPasswordException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.getDialog
import schoenstatt.schoenstapp.isOnline
import schoenstatt.schoenstapp.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    val presenter : SignUpPresenter by currentScope.inject()
    private val disposables = mutableListOf<Disposable>()

    companion object {
        fun getIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun onSignUpClicked(view: View) {
        val disposable = isOnline(this).subscribe({
            if(it) {
                if(input_password.text.isNotEmpty() && input_user.text.isNotEmpty()) {
                        if(isPasswordCorrect()) {
                            val disposable = presenter.signUp(input_user.text.toString(), input_password.text.toString())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe {
                                        when(it) {
                                            is Correct -> getDialog(this, "Verify your email", "We've sent you an email to verify you account")
                                                    ?.setPositiveButton("Ok") {_,_ ->
                                                        startActivity(LoginActivity.getIntent(this))
                                                        finish()
                                                    }
                                                    ?.setCancelable(false)
                                                    ?.show()
                                            is WeakPasswordException -> getDialog(this, "Weak password", "Password should be at least 6 characters")?.show()
                                            is EmailAlreadyInUseException -> getDialog(this, "Email in use", "This email is already in use by another account")?.show()
                                            else -> getDialog(this, getString(R.string.error_unknown_title), getString(R.string.error_unknown_description))?.show()
                                        }
                                    }
                            disposables.add(disposable)
                        } else {
                            Toast.makeText(this, "passwords didn't match", Toast.LENGTH_SHORT).show()
                        }
                } else getDialog(this, "Fields missing", "You have to complete all the fields")?.show()
            } else Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        },{it.printStackTrace()})

        disposables.add(disposable)
    }

    private fun isPasswordCorrect(): Boolean {
        return input_password.text.toString() == input_confirm_password.text.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach {
            it.dispose()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(LoginActivity.getIntent(this))
    }
}
