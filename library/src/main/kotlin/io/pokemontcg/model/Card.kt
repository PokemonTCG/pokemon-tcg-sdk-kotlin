package io.pokemontcg.model


data class Card(
        val id: String,
        val name: String,
        val nationalPokedexNumber: Int?,
        val imageUrl: String,
        val imageUrlHiRes: String,
        val types: List<Type>?,
        val supertype: SuperType,
        val subtype: SubType,
        val evolvesFrom: String?,
        val hp: Int?,
        val retreatCost: List<Type>?,
        val number: String,
        val artist: String?,
        val rarity: String?,
        val series: String,
        val set: String,
        val setCode: String,
        val text: List<String>?,
        val attacks: List<Attack>?,
        val weaknesses: List<Effect>?,
        val resistances: List<Effect>?,
        val ability: Ability?
)