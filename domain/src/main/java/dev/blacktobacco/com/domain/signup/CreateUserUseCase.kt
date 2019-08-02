package dev.blacktobacco.com.domain.signup

import dev.blacktobacco.com.domain.user.UserRepository

class CreateUserUseCase(private val repository: UserRepository) {

    fun execute(user: String, password: String) {
        repository.create(user, password)
    }
}