package dev.blacktobacco.com.data.capital

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import dev.blacktobacco.com.data.Constants.Companion.CAPITALS_COLLECTION_NAME
import dev.blacktobacco.com.domain.capitals.Capital
import dev.blacktobacco.com.domain.capitals.CapitalsRepository
import dev.blacktobacco.com.domain.capitals.CreationCapital
import io.reactivex.Observable



class CapitalsRepositoryImpl: CapitalsRepository {


    val capitals = FirebaseFirestore.getInstance().collection(CAPITALS_COLLECTION_NAME)

    override fun newCapital(capital: Capital): Observable<Boolean> {
       return Observable.create {emitter ->
           capitals.add(capital.toCreationCapital())
                   .addOnSuccessListener {
                       emitter.onNext(true)
                   }
                   .addOnFailureListener{
                       emitter.onNext(false)
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