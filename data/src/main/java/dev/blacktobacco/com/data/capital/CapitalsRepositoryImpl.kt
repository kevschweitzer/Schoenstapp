package dev.blacktobacco.com.data.capital

import com.google.firebase.firestore.FirebaseFirestore
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

}