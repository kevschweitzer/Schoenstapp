package dev.blacktobacco.com.domain.capitals

import io.reactivex.Observable

interface CapitalsRepository {

    fun newCapital(capital: Capital): Observable<Boolean>
    fun getCapitals(): Observable<List<Capital>>
    fun addCapital(id: String): Observable<Int>
    fun joinCapital(id: String): Observable<Boolean>
}