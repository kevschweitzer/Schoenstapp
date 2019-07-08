package schoenstatt.schoenstapp.signup

class SignUpPresenter(private val model: SignUpModel) {

    fun signUp(username: String, email: String, password: String) {
        model.signUp(username, email, password)
    }
}