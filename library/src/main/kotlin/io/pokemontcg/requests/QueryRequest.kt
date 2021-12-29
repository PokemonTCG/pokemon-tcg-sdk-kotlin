package io.pokemontcg.requests

interface QueryRequest<T> : Request<T> {

    fun where(query: QueryBuilder.() -> Unit): WhereRequest<T>
    fun where(query: QueryBuilder): WhereRequest<T>
    suspend fun find(id: String): T
}
