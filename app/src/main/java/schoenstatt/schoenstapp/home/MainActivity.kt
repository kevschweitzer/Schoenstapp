package schoenstatt.schoenstapp.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import schoenstatt.schoenstapp.PhoneActivity
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.main.CapitalsActivity
import schoenstatt.schoenstapp.login.LoginActivity
import schoenstatt.schoenstapp.signup.SignUpActivity


class MainActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toPhone(view: View){
        startActivity(PhoneActivity.getIntent(this))
    }

    fun toCapitals(view: View){
        startActivity(CapitalsActivity.getIntent(this))
    }

    fun logOut(view: View) {
        startActivity(LoginActivity.getIntent(this))
    }
}
