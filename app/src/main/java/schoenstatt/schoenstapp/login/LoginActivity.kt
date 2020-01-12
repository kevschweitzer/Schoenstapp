package schoenstatt.schoenstapp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import schoenstatt.schoenstapp.forgot.ForgotPasswordActivity
import schoenstatt.schoenstapp.getDialog
import schoenstatt.schoenstapp.home.MainActivity
import schoenstatt.schoenstapp.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private val presenter: LoginPresenter by currentScope.inject()
    private val disposables = mutableListOf<Disposable>()

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val user = presenter.getCurrentUser()
        if(user != null) {
            startActivity(MainActivity.getIntent(this))
            finish()
        }
    }

    fun onSignInClicked(view: View) {
        val disposable = presenter.signIn(input_user.text.toString(), input_password.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when(it) {
                        is Correct -> {
                            startActivity(MainActivity.getIntent(this))
                        }
                        is WrongCredentialsException -> getDialog(this, getString(R.string.error_wrong_credentials_title), getString(R.string.error_wrong_credentials_description))?.show()
                        is EmailNotVerifiedError -> getDialog(this, getString(R.string.error_not_validated_title), getString(R.string.error_not_validated_description))?.show()
                        is UnusualActivityException -> getDialog(this, getString(R.string.unusual_activity_title), getString(R.string.unusual_activity_description))
                        else -> getDialog(this, getString(R.string.error_unknown_title), getString(R.string.error_unknown_description))?.show()
                    }
                }
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
