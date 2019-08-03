package schoenstatt.schoenstapp.signup

class SignUpPresenter(private val model: SignUpModel) {

    fun signUp(user: String, password: String) = model.signUp(user, password)


}