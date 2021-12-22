package io.pokemontcg.requests

interface Request<T> {

    suspend fun all(): List<T>
}
