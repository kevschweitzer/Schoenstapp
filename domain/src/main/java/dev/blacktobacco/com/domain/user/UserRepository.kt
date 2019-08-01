package dev.blacktobacco.com.domain.user

interface UserRepository {

    fun create(user: String, password: String)
}