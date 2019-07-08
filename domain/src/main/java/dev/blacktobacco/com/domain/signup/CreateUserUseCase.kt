package dev.blacktobacco.com.domain.signup

class CreateUserUseCase(private val service: AccountService) {

    fun execute(username: String, email: String, password: String) {
        service.createUser(username, email, password)
    }
}