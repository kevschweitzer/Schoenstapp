package dev.blacktobacco.com.data.capital

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import dev.blacktobacco.com.data.Constants.Companion.CAPITALS_COLLECTION_NAME
import dev.blacktobacco.com.data.Constants.Companion.USERS_CAPITALS_COLLECTION_NAME
import dev.blacktobacco.com.data.users_capitals.UsersCapitals
import dev.blacktobacco.com.data.users_capitals.UsersCapitals.Companion.OWNED_IDS_FIELD
import dev.blacktobacco.com.data.users_capitals.UsersCapitals.Companion.USER_ID_FIELD
import dev.blacktobacco.com.domain.capitals.Capital
import dev.blacktobacco.com.domain.capitals.CapitalsRepository
import dev.blacktobacco.com.domain.capitals.CreationCapital
import dev.blacktobacco.com.domain.user.GetCurrentUserUseCase
import io.reactivex.Observable



class CapitalsRepositoryImpl(private val getCurrentUserUseCase: GetCurrentUserUseCase): CapitalsRepository {

    private val db = FirebaseFirestore.getInstance()
    val capitals = db.collection(CAPITALS_COLLECTION_NAME)
    private val usersCapitals = db.collection(USERS_CAPITALS_COLLECTION_NAME)

    override fun newCapital(capital: Capital): Observable<Boolean> {
       return Observable.create {emitter ->
               val userId = getCurrentUserUseCase.execute()?.id
               usersCapitals.whereEqualTo(USER_ID_FIELD, userId).get()
                       .addOnSuccessListener {
                           db.runTransaction { transaction ->
                               val newCapital = capitals.document()
                               transaction.set(newCapital, capital.toCreationCapital())
                               if (it.isEmpty) {
                                   val newUserCapital = usersCapitals.document()
                                   transaction.set(newUserCapital, UsersCapitals(userId!!, listOf(newCapital.id), listOf()))
                               } else {
                                   val userCapital = it.documents[0]
                                   val ownedList = userCapital.get(OWNED_IDS_FIELD) as MutableList<String>
                                   ownedList.add(newCapital.id)
                                   transaction.update(userCapital.reference, OWNED_IDS_FIELD, ownedList)
                               }
                           }.addOnSuccessListener {
                               emitter.onNext(true)
                           }.addOnFailureListener {
                               it.printStackTrace()
                               emitter.onNext(false)
                           }
                       }
       }

    }

    override fun getCapitals(): Observable<List<Capital>> {
        return Observable.create<List<Capital>> {emitter ->
            capitals.get()
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful) {
                            val capitalarios = mutableListOf<Capital>()
                            task.result?.forEach {
                                capitalarios.add(it.toCapital())
                            }
                            emitter.onNext(capitalarios)
                        }
                    }
                    .addOnFailureListener{
                        Log.i("QUERY", "FAILED")
                    }
        }
    }

    override fun addCapital(id: String): Observable<Int> {
        return Observable.create<Int> {emitter ->
            val capital = capitals.document(id)
            capitals.document(id).get()
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            val capitalsQuantity = task.result?.get("capitals") as Long + 1
                            capital.update("capitals", capitalsQuantity)
                            emitter.onNext(capitalsQuantity.toInt())
                        }
                    }
        }

    }

}

fun QueryDocumentSnapshot.toCapital() : Capital {
    val name = get("name") as String
    val password = get("password") as String
    val capitals = get("capitals") as Long
    return Capital(id, name, password, capitals.toInt())
}

fun Capital.toCreationCapital() = CreationCapital(name, password, capitals)