package dev.blacktobacco.com.domain.account.forgot

import dev.blacktobacco.com.domain.account.AccountService

class SendForgotPasswordUseCase(private val service: AccountService) {

    fun execute(mail: String) = service.sendForgotEmail(mail)

}