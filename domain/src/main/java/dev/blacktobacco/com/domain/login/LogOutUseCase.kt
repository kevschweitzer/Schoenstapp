package dev.blacktobacco.com.domain.login

import dev.blacktobacco.com.domain.user.UserRepository

class LogOutUseCase(private val userRepository: UserRepository) {

    fun execute() {
        userRepository.signOut()
    }
}