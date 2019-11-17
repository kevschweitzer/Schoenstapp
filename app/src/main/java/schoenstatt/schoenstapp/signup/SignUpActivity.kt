package schoenstatt.schoenstapp.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.getDialog
import schoenstatt.schoenstapp.home.MainActivity
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
        val disposable = presenter.signUp(input_user.text.toString(), input_password.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it) {
                        getDialog(this, "Verify your email", "We've sent you an email to verify you account")
                                ?.setPositiveButton("Ok") {_,_ ->
                                    startActivity(LoginActivity.getIntent(this))
                                    finish()
                                }
                                ?.setCancelable(false)
                                ?.show()
                    } else {
                        Toast.makeText(this, "Sign up error", Toast.LENGTH_SHORT).show()
                    }
                }
        disposables.add(disposable)
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
