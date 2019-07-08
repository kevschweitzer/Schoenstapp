package dev.blacktobacco.com.data.signup

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import dev.blacktobacco.com.domain.signup.AccountService

class AccountServiceImpl: AccountService {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun createUser(username: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Log.d("SignUp", "createUserWithEmail:success");
                    } else {
                        Log.d("SignUp", "createUserWithEmail:failure");
                    }
                }
    }
}