package dev.blacktobacco.com.domain.capitals

import io.reactivex.Observable

interface CapitalsRepository {

    fun newCapital(capital: Capital): Observable<Boolean>
}