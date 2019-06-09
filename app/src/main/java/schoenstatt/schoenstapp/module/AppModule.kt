package schoenstatt.schoenstapp.module

import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import org.koin.dsl.module
import schoenstatt.schoenstapp.capitals.main.CapitalModel
import schoenstatt.schoenstapp.capitals.main.CapitalsPresenter
import schoenstatt.schoenstapp.capitals.new.NewCapitalModel
import schoenstatt.schoenstapp.capitals.new.NewCapitalViewModel

val appModule = module {

    single { CapitalsPresenter(get()) }

    single { CapitalModel(get()) }

    single { NewCapitalUseCase(get())}

    single {NewCapitalViewModel(get())}

    single {NewCapitalModel()}
}