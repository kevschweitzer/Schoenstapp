package schoenstatt.schoenstapp.capitals.main

import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class CapitalsPresenter(val model: CapitalModel) {

    fun createCapital(capitalProfile: CapitalProfile) = model.createCapital(capitalProfile)
    fun getCapitals() = model.getCapitals()
    fun addCapital(id: String) = model.addCapital(id)


}