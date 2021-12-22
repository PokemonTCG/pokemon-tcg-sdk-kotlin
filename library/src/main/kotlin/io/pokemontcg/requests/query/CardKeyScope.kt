package io.pokemontcg.requests.query

sealed class CardKeyScope<T : QueryValue>(key: String) : KeyScope(key) {

}
