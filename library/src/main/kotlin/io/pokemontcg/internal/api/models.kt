package io.pokemontcg.internal.api


internal class CardResponse(val cards: List<CardModel>)

internal class SetResponse(val sets: List<CardSetModel>)

internal class TypeResponse(val types: List<String>)

internal class SuperTypeResponse(val supertypes: List<String>)

internal class SubTypeResponse(val subtypes: List<String>)


internal class CardModel(
        val id: String,
        val name: String,
        val nationalPokedexNumber: Int?,
        val imageUrl: String,
        val imageUrlHiRes: String,
        val types: List<String>?,
        val supertype: String,
        val subtype: String,
        val evolvesFrom: String?,
        val hp: String?,
        val retreatCost: List<String>?,
        val number: String,
        val artist: String,
        val rarity: String?,
        val series: String,
        val set: String,
        val setCode: String,
        val text: List<String>?,
        val attacks: List<AttackModel>?,
        val weaknesses: List<EffectModel>?,
        val resistances: List<EffectModel>?
)


internal class CardSetModel(
        val code: String,
        val ptcgoCode: String,
        val name: String,
        val series: String,
        val totalCards: Int,
        val standardLegal: Boolean,
        val expandedLegal: Boolean,
        val releaseDate: String,
        val symbolUrl: String,
        val logoUrl: String
)


internal class AttackModel(
        val cost: List<String>?,
        val name: String,
        val text: String?,
        val damage: String,
        val convertedEnergyCost: Int
)


internal class EffectModel(
        val type: String,
        val value: String
)


