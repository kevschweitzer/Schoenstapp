package schoenstatt.schoenstapp.login

import dev.blacktobacco.com.domain.login.LogInUseCase

class LoginModel(private val logInUseCase: LogInUseCase) {

    fun signIn(user: String, password: String) = logInUseCase.execute(user, password)

}