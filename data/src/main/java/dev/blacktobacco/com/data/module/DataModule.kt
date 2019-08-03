package dev.blacktobacco.com.data.module

import androidx.room.Room
import dev.blacktobacco.com.data.capital.CapitalsRepositoryImpl
import dev.blacktobacco.com.data.database.AppDatabase
import dev.blacktobacco.com.data.user.UserRepositoryImpl
import dev.blacktobacco.com.domain.capitals.CapitalsRepository
import dev.blacktobacco.com.domain.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<CapitalsRepository> {CapitalsRepositoryImpl()}

    single<UserRepository> { UserRepositoryImpl(androidContext(), get()) }

    single {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java, "schoenstapp-database"
            ).build()
    }
}