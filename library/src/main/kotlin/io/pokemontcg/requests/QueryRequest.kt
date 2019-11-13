package io.pokemontcg.requests

import io.reactivex.Observable

interface QueryRequest<T, Q : QueryBuilder>: Request<T> {

    fun where(query: Q.() -> Unit): WhereRequest<T>
    fun where(query: Q): WhereRequest<T>
    fun find(id: String): T
    fun observeFind(id: String): Observable<T>
}
