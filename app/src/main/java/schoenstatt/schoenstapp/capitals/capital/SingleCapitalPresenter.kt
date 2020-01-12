package schoenstatt.schoenstapp.capitals.capital

import android.content.Intent
import io.reactivex.Observable
import schoenstatt.schoenstapp.Constants.Companion.SINGLE_CAPITAL
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class SingleCapitalPresenter(private val model: SingleCapitalModel) {

    var tempCapitals = 0

    fun addCapitals(): Observable<Int> {
        val observer = model.addCapitals(tempCapitals)
        tempCapitals = 0
        return observer
    }

    fun handleIntent(intent: Intent) {
        model.capital = intent.getSerializableExtra(SINGLE_CAPITAL) as CapitalProfile
    }

    fun getCapitals() = model.capital.capitals
}