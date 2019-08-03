package schoenstatt.schoenstapp.login

class LoginPresenter(private val model: LoginModel) {

    fun signIn(user: String, password: String) = model.signIn(user, password)
    fun getCurrentUser() = model.getCurrentUser()
}