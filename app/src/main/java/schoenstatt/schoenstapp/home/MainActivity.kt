package schoenstatt.schoenstapp.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.PhoneActivity
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.main.CapitalsActivity
import schoenstatt.schoenstapp.exitApp
import schoenstatt.schoenstapp.heavenwards.HeavenwardsActivity
import schoenstatt.schoenstapp.login.LoginActivity


class MainActivity : AppCompatActivity() {

    private val presenter: MainPresenter by currentScope.inject()

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

    fun toHeavenwards(view: View) {
        startActivity(HeavenwardsActivity.getIntent(this))
    }

    fun logOut(view: View) {
        presenter.logOut()
        startActivity(LoginActivity.getIntent(this))
        finish()
    }

    override fun onBackPressed() {
        exitApp(this)
    }
}
