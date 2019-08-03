package schoenstatt.schoenstapp.signup

import dev.blacktobacco.com.domain.signup.CreateUserUseCase

class SignUpModel(private val createUserUseCase: CreateUserUseCase) {

    fun signUp(user: String, password: String) = createUserUseCase.execute(user, password)

}