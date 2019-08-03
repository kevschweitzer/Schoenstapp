package dev.blacktobacco.com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.blacktobacco.com.data.user.UserDao
import dev.blacktobacco.com.data.user.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}