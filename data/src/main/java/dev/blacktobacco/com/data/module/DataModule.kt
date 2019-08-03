package dev.blacktobacco.com.data.module

import dev.blacktobacco.com.data.capital.CapitalsRepositoryImpl
import dev.blacktobacco.com.data.user.UserRepositoryImpl
import dev.blacktobacco.com.domain.capitals.CapitalsRepository
import dev.blacktobacco.com.domain.user.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<CapitalsRepository> {CapitalsRepositoryImpl()}

    single<UserRepository> { UserRepositoryImpl() }
}