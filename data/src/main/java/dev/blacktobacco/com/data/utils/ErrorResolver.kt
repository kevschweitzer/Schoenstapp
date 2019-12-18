package dev.blacktobacco.com.data.utils

import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.blacktobacco.com.domain.ServerResponse
import dev.blacktobacco.com.domain.UnknownError
import dev.blacktobacco.com.domain.WrongCredentialsException

fun getFirebaseError(error: FirebaseAuthException): ServerResponse {
    return when (error) {
        is FirebaseAuthInvalidCredentialsException -> WrongCredentialsException()
        else -> UnknownError()
    }
}