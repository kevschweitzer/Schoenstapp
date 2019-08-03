package dev.blacktobacco.com.data.user

import com.google.firebase.auth.FirebaseUser
import dev.blacktobacco.com.data.Constants.Companion.EMPTY_STRING
import dev.blacktobacco.com.domain.user.User

fun User.toUserEntity() = UserEntity(id, email)

fun UserEntity.toUser() = User(uid, email)

fun FirebaseUser.toUser() = User(uid, email?:EMPTY_STRING)