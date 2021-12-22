package io.pokemontcg.requests

interface QueryRequest<T, Q : QueryBuilder> : Request<T> {

    fun where(query: Q.() -> Unit): WhereRequest<T>
    fun where(query: Q): WhereRequest<T>
    suspend fun find(id: String): T
}
