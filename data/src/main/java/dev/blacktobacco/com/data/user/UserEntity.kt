package dev.blacktobacco.com.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
        @PrimaryKey var uid: String,
        @ColumnInfo(name = "user_email") var email: String
)