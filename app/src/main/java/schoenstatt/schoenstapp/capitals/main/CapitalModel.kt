package schoenstatt.schoenstapp.capitals.main

import dev.blacktobacco.com.domain.capitals.AddCapitalUseCase
import dev.blacktobacco.com.domain.capitals.Capital
import dev.blacktobacco.com.domain.capitals.GetCapitalsUseCase
import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class CapitalModel(private val newCapitalUseCase: NewCapitalUseCase,
                   private val getCapitalsUseCase: GetCapitalsUseCase,
                   private val addCapitalUseCase: AddCapitalUseCase) {

    fun createCapital(capitalProfile: CapitalProfile) = newCapitalUseCase.execute(capitalProfile.toCapital())
    fun getCapitals() = getCapitalsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.map { capital ->
                    capital.toCapitalProfile()
                }
            }

    fun addCapital(id: String) = addCapitalUseCase.execute(id)


}

fun CapitalProfile.toCapital() = Capital(id, name, password, capitals)

fun Capital.toCapitalProfile() = CapitalProfile(id, name, password, capitals)