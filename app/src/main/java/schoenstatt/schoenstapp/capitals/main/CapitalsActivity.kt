package schoenstatt.schoenstapp.capitals.main

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_capitals.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.new.CapitalProfile
import schoenstatt.schoenstapp.capitals.new.NewCapitalFragment

class CapitalsActivity : AppCompatActivity() {

    private var isMenuOpen: Boolean = false
    private val presenter: CapitalsPresenter by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, CapitalsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capitals)
        setUpCapitals()
    }

    private fun setUpCapitals() {
        capitals_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CapitalsActivity)
            presenter.getCapitals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        adapter = CapitalsAdapter(it)
                    }
        }
    }

    fun newCapital(view: View){
        val newCapitalFragment = NewCapitalFragment()
        newCapitalFragment.newCapitalProfile.observe(this, Observer { newCapitalCreated(it) })
        newCapitalFragment.show(supportFragmentManager, "fragment_new_capital")
    }

    private fun newCapitalCreated(capitalProfile: CapitalProfile?) {
        capitalProfile?.let{
            presenter.createCapital(capitalProfile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if(it) {
                            setUpCapitals()
                            Toast.makeText(this, "Capitalario creado correctamente", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error al crear capitalario", Toast.LENGTH_SHORT).show()
                        }
                    }
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
