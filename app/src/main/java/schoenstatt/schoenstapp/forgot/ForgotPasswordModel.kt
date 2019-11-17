package schoenstatt.schoenstapp.forgot

import dev.blacktobacco.com.domain.account.forgot.SendForgotPasswordUseCase

class ForgotPasswordModel (private val sendForgotPasswordUseCase: SendForgotPasswordUseCase){

    fun onSendForgotMailClicked(mail: String) = sendForgotPasswordUseCase.execute(mail)
}