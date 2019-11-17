package schoenstatt.schoenstapp.forgot

class ForgotPasswordPresenter(private val model: ForgotPasswordModel) {

    fun onSendForgotMailClicked(mail: String) = model.onSendForgotMailClicked(mail)

}