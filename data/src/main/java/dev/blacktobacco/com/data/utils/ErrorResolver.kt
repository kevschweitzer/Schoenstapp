package dev.blacktobacco.com.data.utils

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import dev.blacktobacco.com.domain.*

fun getFirebaseError(error: Exception): ServerResponse {
    return when (error) {
        is FirebaseAuthWeakPasswordException -> WeakPasswordException() //should be before FirebaseAuthInvalidCredentialsException
        is FirebaseAuthInvalidCredentialsException -> WrongCredentialsException()
        is FirebaseTooManyRequestsException -> UnusualActivityException()
        is FirebaseAuthUserCollisionException -> EmailAlreadyInUseException()
        else -> UnknownError()
    }
}