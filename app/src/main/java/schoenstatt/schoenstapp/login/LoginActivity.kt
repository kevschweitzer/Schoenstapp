package schoenstatt.schoenstapp.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.blacktobacco.com.domain.Correct
import dev.blacktobacco.com.domain.EmailNotVerifiedError
import dev.blacktobacco.com.domain.UnusualActivityException
import dev.blacktobacco.com.domain.WrongCredentialsException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.main.CapitalsActivity
import schoenstatt.schoenstapp.forgot.ForgotPasswordActivity
import schoenstatt.schoenstapp.getDialog
import schoenstatt.schoenstapp.home.MainActivity
import schoenstatt.schoenstapp.isOnline
import schoenstatt.schoenstapp.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private val presenter: LoginPresenter by currentScope.inject()
    private val disposables = mutableListOf<Disposable>()
    private var appLinkData: Uri? = null

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appLinkData = intent.data
        val user = presenter.getCurrentUser()
        if(user != null) {
            startNextActivity()
            finish()
        }
    }

    private fun startNextActivity() {
        if(appLinkData != null) {
            startActivity(CapitalsActivity.getIntent(this, appLinkData as Uri))
        } else {
            startActivity(MainActivity.getIntent(this))
        }
    }

    fun onSignInClicked(view: View) {
        val disposable = isOnline(this).subscribe({
            if(it) {
                val email = input_user.text.toString()
                val password = input_password.text.toString()
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    val disposable = presenter.signIn(input_user.text.toString(), input_password.text.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                when(it) {
                                    is Correct -> { startNextActivity() }
                                    is WrongCredentialsException -> getDialog(this, getString(R.string.error_wrong_credentials_title), getString(R.string.error_wrong_credentials_description))?.show()
                                    is EmailNotVerifiedError -> getDialog(this, getString(R.string.error_not_validated_title), getString(R.string.error_not_validated_description))?.show()
                                    is UnusualActivityException -> getDialog(this, getString(R.string.unusual_activity_title), getString(R.string.unusual_activity_description))?.show()
                                    else -> getDialog(this, getString(R.string.error_unknown_title), getString(R.string.error_unknown_description))?.show()
                                }
                            }
                    disposables.add(disposable)
                } else getDialog(this, getString(R.string.error_wrong_credentials_title), getString(R.string.error_wrong_credentials_description))?.show()
            } else Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        },{
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            it.printStackTrace()
        })
        disposables.add(disposable)

    }

    fun onGoToSignUpClicked(view: View) {
        startActivity(SignUpActivity.getIntent(this))
        finish()
    }

    fun onGoToForgotPasswordClicked(view: View) {
        startActivity(ForgotPasswordActivity.getIntent(this))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach {
            it.dispose()
        }
    }
}
