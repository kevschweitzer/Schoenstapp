package schoenstatt.schoenstapp.module

import dev.blacktobacco.com.domain.account.forgot.SendForgotPasswordUseCase
import dev.blacktobacco.com.domain.capitals.*
import dev.blacktobacco.com.domain.login.LogInUseCase
import dev.blacktobacco.com.domain.login.LogOutUseCase
import dev.blacktobacco.com.domain.signup.CreateUserUseCase
import dev.blacktobacco.com.domain.user.GetCurrentUserUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import schoenstatt.schoenstapp.capitals.capital.SingleCapitalActivity
import schoenstatt.schoenstapp.capitals.capital.SingleCapitalModel
import schoenstatt.schoenstapp.capitals.capital.SingleCapitalPresenter
import schoenstatt.schoenstapp.capitals.main.CapitalModel
import schoenstatt.schoenstapp.capitals.main.CapitalsActivity
import schoenstatt.schoenstapp.capitals.main.CapitalsPresenter
import schoenstatt.schoenstapp.forgot.ForgotPasswordActivity
import schoenstatt.schoenstapp.forgot.ForgotPasswordModel
import schoenstatt.schoenstapp.forgot.ForgotPasswordPresenter
import schoenstatt.schoenstapp.home.MainActivity
import schoenstatt.schoenstapp.home.MainModel
import schoenstatt.schoenstapp.home.MainPresenter
import schoenstatt.schoenstapp.login.LoginActivity
import schoenstatt.schoenstapp.login.LoginModel
import schoenstatt.schoenstapp.login.LoginPresenter
import schoenstatt.schoenstapp.signup.SignUpActivity
import schoenstatt.schoenstapp.signup.SignUpModel
import schoenstatt.schoenstapp.signup.SignUpPresenter

val appModule = module {

    scope(named<CapitalsActivity>()) {
        scoped { CapitalModel(get(), get(), get(), get(), get(), get()) }
    }

    scope(named<SignUpActivity>()) {
        scoped { SignUpPresenter(get()) }
        scoped { SignUpModel(get()) }
    }

    scope(named<LoginActivity>()) {
        scoped { LoginPresenter(get()) }
        scoped { LoginModel(get(), get()) }
    }

    scope(named<MainActivity>()) {
        scoped { MainPresenter(get()) }
        scoped { MainModel(get()) }
    }

    scope(named<ForgotPasswordActivity>()) {
        scoped { ForgotPasswordPresenter(get()) }
        scoped { ForgotPasswordModel(get()) }
        scoped { SendForgotPasswordUseCase(get())}
    }

    scope(named<SingleCapitalActivity>()) {
        scoped { SingleCapitalPresenter(get()) }
        scoped { SingleCapitalModel(get()) }
    }

    single { CapitalsPresenter(get()) }

    single { AddCapitalsUseCase(get()) }

    single { GetCapitalsUseCase(get())}

    single { NewCapitalUseCase(get())}

    single { CreateUserUseCase(get()) }

    single { LogInUseCase(get()) }

    single { GetCurrentUserUseCase(get()) }

    single { LogOutUseCase(get()) }

    single { JoinCapitalUseCase(get()) }

    single { DeleteCapitalUseCase(get()) }

    single { ExitCapitalUseCase(get())}

}