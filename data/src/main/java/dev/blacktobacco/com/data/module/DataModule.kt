package dev.blacktobacco.com.data.module

import dev.blacktobacco.com.data.capital.CapitalsServiceImpl
import dev.blacktobacco.com.data.user.UserRepositoryImpl
import dev.blacktobacco.com.domain.capitals.CapitalsService
import dev.blacktobacco.com.domain.user.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<CapitalsService> {CapitalsServiceImpl()}

    single<UserRepository> { UserRepositoryImpl() }
}