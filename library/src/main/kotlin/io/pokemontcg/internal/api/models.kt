package io.pokemontcg.internal.api

import kotlinx.serialization.Serializable

@Serializable
internal class CardResponse(val cards: List<CardModel>)

@Serializable
internal class SetResponse(val sets: List<CardSetModel>)

@Serializable
internal class TypeResponse(val types: List<String>)

@Serializable
internal class SuperTypeResponse(val supertypes: List<String>)

@Serializable
internal class SubTypeResponse(val subtypes: List<String>)

@Serializable
internal class CardModel(
    val id: String,
    val name: String,
    val nationalPokedexNumber: Int? = null,
    val imageUrl: String,
    val imageUrlHiRes: String,
    val types: List<String>? = null,
    val supertype: String,
    val subtype: String? = null,
    val evolvesFrom: String? = null,
    val hp: String? = null,
    val retreatCost: List<String>? = null,
    val convertedRetreatCost: Int? = null,
    val number: String,
    val artist: String? = null,
    val rarity: String? = null,
    val series: String,
    val set: String? = null,
    val setCode: String,
    val text: List<String>? = null,
    val attacks: List<AttackModel>? = null,
    val weaknesses: List<EffectModel>? = null,
    val resistances: List<EffectModel>? = null,
    val ability: AbilityModel? = null,
    val ancientTrait: AbilityModel? = null
)

@Serializable
internal class CardSetModel(
    val code: String,
    val ptcgoCode: String? = null,
    val name: String,
    val series: String,
    val totalCards: Int,
    val standardLegal: Boolean,
    val expandedLegal: Boolean,
    val releaseDate: String,
    val symbolUrl: String,
    val logoUrl: String,
    val updatedAt: String
)

@Serializable
internal class AbilityModel(
    val name: String,
    val text: String,
    val type: String? = null
)

@Serializable
internal class AttackModel(
    val cost: List<String>? = null,
    val name: String,
    val text: String? = null,
    val damage: String? = null,
    val convertedEnergyCost: Int
)

@Serializable
internal class EffectModel(
    val type: String,
    val value: String
)

