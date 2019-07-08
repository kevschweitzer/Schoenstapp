package schoenstatt.schoenstapp.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private val presenter: LoginPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLoginClicked(view: View) {
        presenter.login(username_input.text.toString(), password_input.text.toString())
    }

    fun onSignUpClicked(view: View) {
        startActivity(SignUpActivity.getIntent(this))
    }
}
