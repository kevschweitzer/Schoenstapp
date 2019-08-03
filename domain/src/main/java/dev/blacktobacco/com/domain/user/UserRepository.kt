package dev.blacktobacco.com.domain.user

import io.reactivex.Observable

interface UserRepository {

    fun create(user: String, password: String): Observable<Boolean>

    fun exists(user: String, password: String): Observable<Boolean>
}