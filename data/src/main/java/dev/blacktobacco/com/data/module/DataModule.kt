package dev.blacktobacco.com.data.module

import dev.blacktobacco.com.data.capital.CapitalsServiceImpl
import dev.blacktobacco.com.data.signup.AccountServiceImpl
import dev.blacktobacco.com.domain.capitals.CapitalsService
import dev.blacktobacco.com.domain.signup.AccountService
import org.koin.dsl.module

val dataModule = module {

    single<CapitalsService> {CapitalsServiceImpl()}

    single<AccountService> {AccountServiceImpl()}
}