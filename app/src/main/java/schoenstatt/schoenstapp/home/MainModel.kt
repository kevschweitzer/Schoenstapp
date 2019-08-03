package schoenstatt.schoenstapp.home

import dev.blacktobacco.com.domain.login.LogOutUseCase

class MainModel(private val logOutUseCase: LogOutUseCase) {

    fun logOut() {
        logOutUseCase.execute()
    }
}