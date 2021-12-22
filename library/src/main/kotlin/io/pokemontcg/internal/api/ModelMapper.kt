package io.pokemontcg.internal.api

import io.pokemontcg.model.*

internal object ModelMapper {

  fun toCards(models: Iterable<CardModel>): List<Card> {
    return models.map { to(it) }
  }

  fun to(model: CardModel): Card {
    return Card(
      id = model.id,
      name = model.name,
      supertype = SuperType.find(model.supertype),
      subtypes = model.subtypes ?: emptyList(),
      level = model.level,
      hp = model.hp,
      types = model.types?.map { Type.find(it) },
      evolvesFrom = model.evolvesFrom,
      evolvesTo = model.evolvesTo,
      rules = model.rules,
      ancientTrait = model.ancientTrait?.let { to(it) },
      abilities = model.abilities?.map { to(it) },
      attacks = model.attacks?.map { to(it) },
      weaknesses = model.weaknesses?.map { to(it) },
      resistances = model.resistances?.map { to(it) },
      retreatCost = model.retreatCost?.map { Type.find(it) },
      convertedRetreatCost = model.convertedRetreatCost,
      number = model.number,
      set = to(model.set),
      artist = model.artist,
      rarity = model.rarity,
      flavorText = model.flavorText,
      nationalPokedexNumbers = model.nationalPokedexNumbers,
      legalities = model.legalities?.let { to(it) },
      images = Card.Images(
        small = model.images.small,
        large = model.images.large,
      ),
      tcgPlayer = model.tcgplayer?.let { to(it) },
      cardMarket = model.cardmarket?.let { to(it) },
    )
  }

  fun to(model: AbilityModel): Ability {
    return Ability(
      name = model.name,
      text = model.text,
      type = model.type
    )
  }

  fun to(model: AttackModel): Attack {
    return Attack(
      cost = model.cost?.map { Type.find(it) },
      name = model.name,
      text = model.text,
      damage = model.damage,
      convertedEnergyCost = model.convertedEnergyCost
    )
  }

  fun to(model: EffectModel): Effect {
    return Effect(Type.find(model.type), model.value)
  }

  fun toSets(models: Iterable<CardSetModel>): List<CardSet> {
    return models.map { to(it) }
  }

  fun to(model: CardSetModel): CardSet {
    return CardSet(
      id = model.id,
      name = model.name,
      series = model.series,
      printedTotal = model.printedTotal,
      total = model.total,
      legalities = Legalities(
        unlimited = Legality.from(model.legalities.unlimited),
        standard = Legality.from(model.legalities.standard),
        expanded = Legality.from(model.legalities.expanded),
      ),
      ptcgoCode = model.ptcgoCode,
      releaseDate = model.releaseDate,
      updatedAt = model.updatedAt,
      images = CardSet.Images(
        symbol = model.images.symbol,
        logo = model.images.logo,
      )
    )
  }

  fun to(model: LegalitiesModel): Legalities {
    return Legalities(
      unlimited = Legality.from(model.unlimited),
      standard = Legality.from(model.standard),
      expanded = Legality.from(model.expanded),
    )
  }

  fun to(model: TcgPlayerModel): Card.TcgPlayer {
    return Card.TcgPlayer(
      url = model.url,
      updatedAt = model.updated,
      prices = model.prices?.let { prices ->
        Card.TcgPlayer.Prices(
          low = prices.low,
          mid = prices.mid,
          high = prices.high,
          market = prices.market,
          directLow = prices.directLow,
        )
      }
    )
  }

  fun to(model: CardMarketModel): Card.CardMarket {
    return Card.CardMarket(
      url = model.url,
      updatedAt = model.updatedAt,
      prices = model.prices?.let { prices ->
        Card.CardMarket.Prices(
          averageSellPrice = prices.averageSellPrice,
          lowPrice = prices.lowPrice,
          trendPrice = prices.trendPrice,
          germanProLow = prices.germanProLow,
          suggestedPrice = prices.suggestedPrice,
          reverseHoloSell = prices.reverseHoloSell,
          reverseHoloLow = prices.reverseHoloLow,
          reverseHoloTrend = prices.reverseHoloTrend,
          lowPriceExPlus = prices.lowPriceExPlus,
          avg1 = prices.avg1,
          avg7 = prices.avg7,
          avg30 = prices.avg30,
          reverseHoloAvg1 = prices.reverseHoloAvg1,
          reverseHoloAvg7 = prices.reverseHoloAvg7,
          reverseHoloAvg30 = prices.reverseHoloAvg30,
        )
      }
    )
  }
}
