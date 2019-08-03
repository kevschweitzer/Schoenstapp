package dev.blacktobacco.com.domain.login

import dev.blacktobacco.com.domain.user.UserRepository

class LogInUseCase(private val repository: UserRepository) {

    fun execute(user: String, password: String) = repository.exists(user, password)

}