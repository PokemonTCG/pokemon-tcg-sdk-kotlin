package io.pokemontcg.internal.api

import io.pokemontcg.model.*

internal object ModelMapper {

    fun to(model: CardModel): Card {
        return Card(
            model.id,
            model.name,
            model.nationalPokedexNumber,
            model.imageUrl,
            model.imageUrlHiRes,
            model.types?.map { Type.find(it) },
            SuperType.find(model.supertype),
            SubType.find(model.subtype),
            model.evolvesFrom,
            model.hp?.toIntOrNull(),
            model.retreatCost?.map { Type.find(it) },
            model.number,
            model.artist,
            model.rarity,
            model.series,
            model.set,
            model.setCode,
            model.text,
            model.attacks?.map { to(it) },
            model.weaknesses?.map { to(it) },
            model.resistances?.map { to(it) },
            model.ability?.let { to(it) }
        )
    }

    fun to(model: AbilityModel): Ability {
        return Ability(model.name, model.text, model.type)
    }

    fun to(model: AttackModel): Attack {
        return Attack(
            model.cost?.map { Type.find(it) },
            model.name,
            model.text,
            model.damage,
            model.convertedEnergyCost
        )
    }

    fun to(model: EffectModel): Effect {
        return Effect(Type.find(model.type), model.value)
    }

    fun to(model: CardSetModel): CardSet {
        return CardSet(
            model.code,
            model.ptcgoCode,
            model.name,
            model.series,
            model.totalCards,
            model.standardLegal,
            model.expandedLegal,
            model.releaseDate,
            model.symbolUrl,
            model.logoUrl,
            model.updatedAt
        )
    }
}
