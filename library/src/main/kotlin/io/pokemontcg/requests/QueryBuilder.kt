package io.pokemontcg.requests


interface QueryBuilder {

    fun toParams(): Map<String, String?>
}