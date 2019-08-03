package schoenstatt.schoenstapp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R
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
            Log.i("USER EMAIL", user.email )
            finish()
        }
    }

    fun onSignInClicked(view: View) {
        val disposable = presenter.signIn(input_user.text.toString(), input_password.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it) {
                        startActivity(MainActivity.getIntent(this))
                        finish()
                    } else {
                        Toast.makeText(this, "Log in failed", Toast.LENGTH_SHORT).show()
                    }
                }
        disposables.add(disposable)
    }

    fun onGoToSignUpClicked(view: View) {
        startActivity(SignUpActivity.getIntent(this))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach {
            it.dispose()
        }
    }
}
