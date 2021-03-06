package dev.blacktobacco.com.domain.capitals

import io.reactivex.Observable

interface CapitalsRepository {

    fun newCapital(capital: Capital): Observable<Boolean>
    fun getCapitals(): Observable<List<Capital>>
    fun addCapitals(id: String, capitalsAmount: Int): Observable<Int>
    fun joinCapital(id: String): Observable<Boolean>
    fun deleteCapital(id: String): Observable<Boolean>
    fun exitCapital(id: String): Observable<Boolean>
}