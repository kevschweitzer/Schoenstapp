package dev.blacktobacco.com.domain.user

import dev.blacktobacco.com.domain.ServerResponse
import io.reactivex.Observable

interface UserRepository {

    fun create(user: String, password: String): Observable<ServerResponse>

    fun exists(user: String, password: String): Observable<ServerResponse>

    fun getCurrentUser(): User?
    fun signOut()
}