package schoenstatt.schoenstapp.module

import dev.blacktobacco.com.domain.capitals.GetCapitalsUseCase
import dev.blacktobacco.com.domain.capitals.NewCapitalUseCase
import dev.blacktobacco.com.domain.login.LogInUseCase
import dev.blacktobacco.com.domain.signup.CreateUserUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import schoenstatt.schoenstapp.capitals.main.CapitalModel
import schoenstatt.schoenstapp.capitals.main.CapitalsActivity
import schoenstatt.schoenstapp.capitals.main.CapitalsPresenter
import schoenstatt.schoenstapp.login.LoginActivity
import schoenstatt.schoenstapp.login.LoginModel
import schoenstatt.schoenstapp.login.LoginPresenter
import schoenstatt.schoenstapp.signup.SignUpActivity
import schoenstatt.schoenstapp.signup.SignUpModel
import schoenstatt.schoenstapp.signup.SignUpPresenter

val appModule = module {

    scope(named<CapitalsActivity>()) {
        scoped { CapitalsPresenter(get()) }
        scoped { CapitalModel(get(), get()) }
    }

    scope(named<SignUpActivity>()) {
        scoped { SignUpPresenter(get()) }
        scoped { SignUpModel(get()) }
    }

    scope(named<LoginActivity>()) {
        scoped { LoginPresenter(get()) }
        scoped { LoginModel(get()) }
    }

    single { GetCapitalsUseCase(get())}

    single { NewCapitalUseCase(get())}

    single { CreateUserUseCase(get()) }

    single { LogInUseCase(get()) }
}