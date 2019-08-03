package dev.blacktobacco.com.domain.user

class GetCurrentUserUseCase(private val userRepository: UserRepository) {

    fun execute(): User? = userRepository.getCurrentUser()
}