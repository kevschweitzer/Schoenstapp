package dev.blacktobacco.com.domain.account

import io.reactivex.Observable

interface AccountService {

    fun sendForgotEmail(mail: String): Observable<Boolean>
}