package schoenstatt.schoenstapp.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import schoenstatt.schoenstapp.PhoneActivity
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.main.CapitalsActivity


class MainActivity : AppCompatActivity() {

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
}
