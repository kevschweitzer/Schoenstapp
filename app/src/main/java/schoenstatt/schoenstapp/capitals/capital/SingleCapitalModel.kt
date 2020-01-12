package schoenstatt.schoenstapp.capitals.capital

import dev.blacktobacco.com.domain.capitals.AddCapitalsUseCase
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class SingleCapitalModel(private val addCapitalsUseCase: AddCapitalsUseCase) {

    lateinit var capital: CapitalProfile

    fun addCapitals(capitalsToAdd: Int) = addCapitalsUseCase.execute(capital.id, capitalsToAdd)
}