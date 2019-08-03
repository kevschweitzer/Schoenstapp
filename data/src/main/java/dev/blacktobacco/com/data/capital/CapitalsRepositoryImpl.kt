package dev.blacktobacco.com.data.capital

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import dev.blacktobacco.com.data.Constants.Companion.CAPITALS_COLLECTION_NAME
import dev.blacktobacco.com.domain.capitals.Capital
import dev.blacktobacco.com.domain.capitals.CapitalsRepository
import io.reactivex.Observable

class CapitalsRepositoryImpl: CapitalsRepository {

    val capitals = FirebaseFirestore.getInstance().collection(CAPITALS_COLLECTION_NAME)

    override fun newCapital(capital: Capital): Observable<Boolean> {
       return Observable.create {emitter ->
           capitals.add(capital)
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

}

fun QueryDocumentSnapshot.toCapital() : Capital {
    val name = get("name") as String
    val password = get("password") as String
    val capitals = get("capitals") as Long
    return Capital(name, password, capitals.toInt())
}