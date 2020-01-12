package dev.blacktobacco.com.data.utils

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.blacktobacco.com.domain.ServerResponse
import dev.blacktobacco.com.domain.UnknownError
import dev.blacktobacco.com.domain.UnusualActivityException
import dev.blacktobacco.com.domain.WrongCredentialsException

fun getFirebaseError(error: Exception): ServerResponse {
    return when (error) {
        is FirebaseAuthInvalidCredentialsException -> WrongCredentialsException()
        is FirebaseTooManyRequestsException -> UnusualActivityException()
        else -> UnknownError()
    }
}