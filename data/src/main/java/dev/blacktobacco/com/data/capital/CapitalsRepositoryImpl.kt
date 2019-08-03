package dev.blacktobacco.com.data.capital

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dev.blacktobacco.com.domain.capitals.Capital
import dev.blacktobacco.com.domain.capitals.CapitalsRepository

class CapitalsRepositoryImpl: CapitalsRepository {

    val capitals = FirebaseFirestore.getInstance().collection("capitalarios")

    override fun newCapital(capital: Capital) {
       capitals.add(capital)
               .addOnSuccessListener {
                    Log.i("CREATION", "SUCCESS")
               }
               .addOnFailureListener{
                   Log.i("CREATION", "FAILURE")
               }
    }

}