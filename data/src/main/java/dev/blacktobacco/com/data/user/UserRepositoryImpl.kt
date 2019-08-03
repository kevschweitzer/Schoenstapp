package dev.blacktobacco.com.data.user

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import dev.blacktobacco.com.domain.user.UserRepository
import io.reactivex.Observable


class UserRepositoryImpl(): UserRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun create(user: String, password: String): Observable<Boolean> {
        return Observable.create{ emitter ->
            auth.createUserWithEmailAndPassword(user, password)
                    .addOnCompleteListener { task ->
                        emitter.onNext(task.isSuccessful)
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success")
                            //val user = auth.getCurrentUser()
                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.exception)
                            //updateUI(null)
                        }

                        // ...
                    }
        }
    }

    override fun exists(user: String, password: String): Observable<Boolean> {
        return Observable.create { emitter ->
            auth.signInWithEmailAndPassword(user, password)
                    .addOnCompleteListener { task ->
                        emitter.onNext(task.isSuccessful)
                    }
        }

    }
}