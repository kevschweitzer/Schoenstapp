package dev.blacktobacco.com.domain.signup

interface AccountService {
    fun createUser(username: String, email: String, password: String)
}