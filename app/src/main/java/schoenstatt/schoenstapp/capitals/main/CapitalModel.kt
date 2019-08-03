package schoenstatt.schoenstapp.capitals.main

import dev.blacktobacco.com.domain.capitals.Capital
import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class CapitalModel(val newCapitalUseCase: NewCapitalUseCase) {

    fun createCapital(capitalProfile: CapitalProfile) {
        newCapitalUseCase.execute(capitalProfile.toCapital())
    }
}

fun CapitalProfile.toCapital() = Capital(name, password, capitals)