package dev.blacktobacco.com.data.user

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import dev.blacktobacco.com.data.Constants.Companion.CURRENT_USER_UID
import dev.blacktobacco.com.data.Constants.Companion.EMPTY_STRING
import dev.blacktobacco.com.data.Constants.Companion.SHARED_PREFERENCES
import dev.blacktobacco.com.data.database.AppDatabase
import dev.blacktobacco.com.data.utils.getFirebaseError
import dev.blacktobacco.com.domain.Correct
import dev.blacktobacco.com.domain.EmailNotVerifiedError
import dev.blacktobacco.com.domain.ServerResponse
import dev.blacktobacco.com.domain.user.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class UserRepositoryImpl(private val context: Context,
                         private val appDatabase: AppDatabase): UserRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun create(user: String, password: String): Observable<Boolean> {
        return Observable.create{ emitter ->
            auth.createUserWithEmailAndPassword(user, password)
                    .addOnCompleteListener { task ->
                        emitter.onNext(task.isSuccessful)
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success")
                            //saveUser(auth.currentUser)
                            try {
                                auth.currentUser?.sendEmailVerification()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            signOut()
                            //setCurrentUserUid(auth.currentUser?.uid)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        }
                    }
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    private fun setCurrentUserUid(uid: String?) {
        uid?.let {
            val editor = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
            editor.putString(CURRENT_USER_UID, uid)
            editor.apply()
        }
    }

    override fun getCurrentUser() = auth.currentUser?.toUser()

    private fun saveUser(currentUser: FirebaseUser?) {
        Observable.fromCallable {
            currentUser?.let {
                val user = UserEntity(currentUser.uid, currentUser.email ?: EMPTY_STRING)
                appDatabase.userDao().insert(user)
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }

    override fun exists(user: String, password: String): Observable<ServerResponse> {
        return Observable.create { emitter ->
            auth.signInWithEmailAndPassword(user, password)
                    .addOnSuccessListener {
                        if(auth.currentUser?.isEmailVerified == true) {
                            saveUser(auth.currentUser)
                            setCurrentUserUid(auth.currentUser?.uid)
                            emitter.onNext(Correct())
                        } else {
                            signOut()
                            emitter.onNext(EmailNotVerifiedError())
                        }
                    }
                    .addOnFailureListener {
                        emitter.onNext(getFirebaseError((it)))
                        it.printStackTrace()
                    }
        }

    }
}