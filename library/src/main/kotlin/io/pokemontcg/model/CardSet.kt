package io.pokemontcg.model


data class CardSet(
     val code: String,
     val ptcgoCode: String?,
     val name: String,
     val series: String,
     val totalCards: Int,
     val standardLegal: Boolean,
     val expandedLegal: Boolean,
     val releaseDate: String,
     val symbolUrl: String
)