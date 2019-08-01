package schoenstatt.schoenstapp.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.ext.android.inject
import schoenstatt.schoenstapp.R

class SignUpActivity : AppCompatActivity() {

    val presenter : SignUpPresenter by inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun onSignUpClicked(view: View) {
        presenter.signUp(input_user.text.toString(), input_password.text.toString())
    }
}
