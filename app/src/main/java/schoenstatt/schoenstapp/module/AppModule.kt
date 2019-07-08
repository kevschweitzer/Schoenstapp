package schoenstatt.schoenstapp.module

import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import dev.blacktobacco.com.domain.signup.CreateUserUseCase
import org.koin.dsl.module
import schoenstatt.schoenstapp.capitals.main.CapitalModel
import schoenstatt.schoenstapp.capitals.main.CapitalsPresenter
import schoenstatt.schoenstapp.capitals.new.CapitalPresenter
import schoenstatt.schoenstapp.login.LoginModel
import schoenstatt.schoenstapp.login.LoginPresenter
import schoenstatt.schoenstapp.signup.SignUpActivity
import schoenstatt.schoenstapp.signup.SignUpModel
import schoenstatt.schoenstapp.signup.SignUpPresenter

val appModule = module {

    single { CapitalsPresenter(get()) }

    single { CapitalModel(get()) }

    single { NewCapitalUseCase(get())}

    single { CapitalPresenter() }

    single { LoginModel() }

    single { LoginPresenter(get())}

    single { SignUpModel(get()) }

    single { SignUpPresenter(get())}

    single {CreateUserUseCase(get())}
}