package schoenstatt.schoenstapp.capitals.main

import dev.blacktobacco.com.domain.capitals.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class CapitalModel(private val newCapitalUseCase: NewCapitalUseCase,
                   private val getCapitalsUseCase: GetCapitalsUseCase,
                   private val addCapitalUseCase: AddCapitalUseCase,
                   private val joinCapitalUseCase: JoinCapitalUseCase) {

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
    fun joinCapital(id: String) = joinCapitalUseCase.execute(id)
}

fun CapitalProfile.toCapital() = Capital(id, name, password, capitals)

fun Capital.toCapitalProfile() = CapitalProfile(id, name, password, capitals)