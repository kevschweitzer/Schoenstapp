package schoenstatt.schoenstapp.module

import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import org.koin.dsl.module
import schoenstatt.schoenstapp.capitals.main.CapitalModel
import schoenstatt.schoenstapp.capitals.main.CapitalsPresenter

val appModule = module {

    single { CapitalsPresenter(get()) }

    single { CapitalModel(get()) }

    single { NewCapitalUseCase(get())}
}