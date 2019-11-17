package dev.blacktobacco.com.data.account

import com.google.firebase.auth.FirebaseAuth
import dev.blacktobacco.com.domain.account.AccountService
import io.reactivex.Observable

class AccountServiceImpl: AccountService {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun sendForgotEmail(mail: String): Observable<Boolean> {
        return Observable.create { emitter ->
            auth.sendPasswordResetEmail(mail)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onNext(true)
                        } else {
                            emitter.onNext(false)
                        }
                    }
        }
    }
}