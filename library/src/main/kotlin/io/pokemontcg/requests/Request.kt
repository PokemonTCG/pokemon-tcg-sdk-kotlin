package io.pokemontcg.requests

import io.reactivex.Observable

interface Request<T> {

    fun all(): List<T>
    fun observeAll(): Observable<List<T>>
}
