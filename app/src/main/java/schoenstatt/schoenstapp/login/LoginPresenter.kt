package schoenstatt.schoenstapp.login

class LoginPresenter(private val model: LoginModel) {

    fun login(username: String, password: String) {
        model.login(username, password)
    }

}