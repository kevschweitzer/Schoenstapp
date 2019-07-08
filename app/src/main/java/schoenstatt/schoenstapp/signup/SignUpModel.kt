package schoenstatt.schoenstapp.signup

import dev.blacktobacco.com.domain.signup.CreateUserUseCase

class SignUpModel(private val createUserUseCase: CreateUserUseCase) {

    fun signUp(username: String, email: String, password: String) {
        createUserUseCase.execute(username, email, password)
    }
}