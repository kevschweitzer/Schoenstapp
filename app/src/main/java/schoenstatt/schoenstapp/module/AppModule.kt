package schoenstatt.schoenstapp.module

import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import dev.blacktobacco.com.domain.signup.CreateUserUseCase
import org.koin.dsl.module
import schoenstatt.schoenstapp.capitals.main.CapitalModel
import schoenstatt.schoenstapp.capitals.main.CapitalsPresenter
import schoenstatt.schoenstapp.signup.SignUpModel
import schoenstatt.schoenstapp.signup.SignUpPresenter

val appModule = module {

    single { CapitalsPresenter(get()) }

    single { CapitalModel(get()) }

    single { NewCapitalUseCase(get())}

    single { SignUpPresenter(get()) }

    single { SignUpModel(get()) }

    single { CreateUserUseCase(get()) }
}