package dev.blacktobacco.com.data.users_capitals

import com.google.firebase.firestore.FirebaseFirestore
import dev.blacktobacco.com.data.Constants
import dev.blacktobacco.com.domain.user.GetCurrentUserUseCase
import dev.blacktobacco.com.domain.users_capitals.UsersCapitalsRepository

class UsersCapitalsRepositoryImpl(private val getCurrentUserUseCase: GetCurrentUserUseCase): UsersCapitalsRepository {

    val usersCapitals = FirebaseFirestore.getInstance().collection(Constants.USERS_CAPITALS_COLLECTION_NAME)

    override fun createAssociationForUser(uid: String) {
        usersCapitals.add(UsersCapitals(userId = uid))
    }

    override fun addOwnedCapital(capitalId: String) {
        val user = getCurrentUserUseCase.execute()
        val currentUserCapitals = usersCapitals.whereEqualTo("user_id", user?.id)
        currentUserCapitals.get()
                .addOnSuccessListener {

                }

    }
}