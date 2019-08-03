package schoenstatt.schoenstapp.login

import dev.blacktobacco.com.domain.login.LogInUseCase
import dev.blacktobacco.com.domain.user.GetCurrentUserUseCase

class LoginModel(private val logInUseCase: LogInUseCase,
                 private val getCurrentUserUseCase: GetCurrentUserUseCase) {

    fun signIn(user: String, password: String) = logInUseCase.execute(user, password)

    fun getCurrentUser() = getCurrentUserUseCase.execute()


}