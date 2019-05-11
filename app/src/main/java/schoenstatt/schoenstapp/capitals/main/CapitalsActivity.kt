package schoenstatt.schoenstapp.capitals.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_capitals.*
import org.koin.android.ext.android.inject
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.new.CapitalProfile
import schoenstatt.schoenstapp.capitals.new.NewCapitalFragment

class CapitalsActivity : AppCompatActivity() {

    private var isMenuOpen: Boolean = false
    private val presenter: CapitalsPresenter by inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, CapitalsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capitals)
    }

    fun newCapital(view: View){
        val newCapitalFragment = NewCapitalFragment()
        newCapitalFragment.newCapitalProfile.observe(this, Observer { newCapitalCreated(it) })
        newCapitalFragment.show(supportFragmentManager, "fragment_new_capital")
    }

    private fun newCapitalCreated(capitalProfile: CapitalProfile?) {
        capitalProfile?.let{
            presenter.createCapital(capitalProfile)
        }
    }

    fun handleMenu(view: View){
        if(isMenuOpen){
            closeMenu()
        } else {
            openMenu()
        }
    }

    private fun openMenu() {
        isMenuOpen = true
        new_capital.visibility = View.VISIBLE
        join_capital.visibility = View.VISIBLE
        new_capital.animate().translationY(-90f)
        join_capital.animate().translationY(-180f)
    }

    private fun closeMenu() {
        isMenuOpen = false
        new_capital.animate().translationY(0f)
        join_capital.animate().translationY(0f).withEndAction {
            new_capital.visibility = View.GONE
            join_capital.visibility = View.GONE
        }
    }


}
