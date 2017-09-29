package io.pokemontcg.model


data class Attack(
        val cost: List<String>,
        val name: String,
        val text: String,
        val damage: String,
        val convertedEnergyCost: Int
)