package io.pokemontcg.requests.query

import io.pokemontcg.ExperimentalPokemonApi
import io.pokemontcg.model.SuperType
import io.pokemontcg.model.Type
import java.lang.StringBuilder

abstract class Builder : QueryComponent {

  val components = mutableListOf<QueryComponent>()

  override fun build(): String {
    return components.joinToString(separator = " ") { it.build() }
  }
}

@ExperimentalPokemonApi
fun cardBuilder(block: CardBuilder.() -> Unit): String {
  val builder = CardBuilder()
  builder.block()
  return builder.build()
}

@ExperimentalPokemonApi
class CardBuilder : Builder() {

  fun id(value: String) {
    components += StringValue("id:$value")
  }

  fun id(vararg values: String) {
    ids(values.toList())
  }

  fun ids(values: List<String>) {
    components += StringKeyScope("id").apply {
      isIn(values)
    }
  }

  fun id(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("id").apply(block)
  }

  fun name(value: String) {
    components += StringValue("name:$value")
  }

  fun name(vararg value: String) {
    names(value.toList())
  }

  fun names(values: Iterable<String>) {
    components += StringKeyScope("name").apply {
      isIn(values)
    }
  }

  fun name(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("name").apply(block)
  }

  fun flavorText(value: String) {
    components += StringValue("flavorText:$value")
  }

  fun hp(value: String) {
    components += StringValue("hp:$value")
  }

  fun hp(value: Int) {
    components += StringValue("hp:$value")
  }

  fun hp(block: IntRangeKeyScope.() -> Unit) {
    components += IntRangeKeyScope("hp").apply(block)
  }

  fun ancientTrait(block: AbilityBuilder.() -> Unit) {
    components += AbilityBuilder("ancientTrait").apply(block)
  }

  fun abilities(block: AbilityBuilder.() -> Unit) {
    components += AbilityBuilder("abilities").apply(block)
  }

  fun attacks(block: AttackBuilder.() -> Unit) {
    components += AttackBuilder("attacks").apply(block)
  }

  fun weaknesses(block: EffectBuilder.() -> Unit) {
    components += EffectBuilder("weaknesses").apply(block)
  }

  fun resistances(block: EffectBuilder.() -> Unit) {
    components += EffectBuilder("resistances").apply(block)
  }

  fun set(block: SetBuilder.() -> Unit) {
    components += SetBuilder("set").apply(block)
  }

  fun artist(value: String) {
    components += StringValue("artist:$value")
  }

  fun rarity(value: String) {
    components += StringValue("rarity:$value")
  }

  fun rarity(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("rarity").apply(block)
  }

  fun supertype(superType: SuperType) {
    components += StringValue("supertype:${superType.text}")
  }

  fun type(value: String) {
    components += StringValue("types:$value")
  }

  fun type(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("types").apply(block)
  }

  fun type(vararg values: String) {
    components += StringKeyScope("types").apply {
      isIn(values.toList())
    }
  }

  fun type(value: Type) {
    type(value.displayName)
  }

  fun type(vararg values: Type) {
    type(values.toList())
  }

  fun type(values: Iterable<Type>) {
    components += StringKeyScope("types").apply {
      isIn(values.map { it.displayName })
    }
  }

  fun subtypes(value: String) {
    components += StringValue("subtypes:$value")
  }

  fun subtypes(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("subtypes").apply(block)
  }

  fun retreatCost(value: String) {
    components += StringValue("retreatCost:$value")
  }

  fun retreatCost(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("retreatCost").apply(block)
  }

  fun convertedRetreatCost(value: String) {
    components += StringValue("convertedRetreatCost:$value")
  }

  fun convertedRetreatCost(value: Int) {
    components += StringValue("convertedRetreatCost:$value")
  }

  fun convertedRetreatCost(block: IntRangeKeyScope.() -> Unit) {
    components += IntRangeKeyScope("convertedRetreatCost").apply(block)
  }

  fun evolvesFrom(value: String) {
    components += StringValue("evolvesFrom:$value")
  }

  fun evolvesFrom(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("evolvesFrom").apply(block)
  }

  fun evolvesTo(value: String) {
    components += StringValue("evolvesTo:$value")
  }

  fun evolvesTo(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("evolvesTo").apply(block)
  }
}

class AbilityBuilder(private val key: String) : Builder() {

  fun name(value: String) {
    components += StringValue("$key.name:$value")
  }

  fun name(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.name").apply(block)
  }

  fun text(value: String) {
    components += StringValue("$key.text:$value")
  }

  fun text(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.text").apply(block)
  }

  fun type(value: String) {
    components += StringValue("$key.type:$value")
  }

  fun type(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.type").apply(block)
  }
}

class AttackBuilder(private val key: String) : Builder() {

  fun name(value: String) {
    components += StringValue("$key.name:$value")
  }

  fun name(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.name").apply(block)
  }

  fun text(value: String) {
    components += StringValue("$key.text:$value")
  }

  fun text(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.text").apply(block)
  }

  fun convertedEnergyCost(value: Int) {
    components += StringValue("$key.convertedEnergyCost:$value")
  }

  fun convertedEnergyCost(block: IntRangeKeyScope.() -> Unit) {
    components += IntRangeKeyScope("convertedEnergyCost").apply(block)
  }

  fun cost(value: String) {
    components += StringValue("$key.cost:$value")
  }

  fun cost(vararg values: String) {
    components += StringKeyScope("$key.cost").apply {
      isIn(values.toList())
    }
  }

  fun cost(value: Type) {
    cost(value.displayName)
  }

  fun cost(vararg values: Type) {
    components += StringKeyScope("$key.cost").apply {
      isIn(values.map { it.displayName })
    }
  }

  fun cost(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.cost").apply(block)
  }

  fun damage(value: String) {
    components += StringValue("$key.damage:$value")
  }

  fun damage(value: Int) {
    damage(value.toString())
  }

  fun damage(block: IntRangeKeyScope.() -> Unit) {
    components += IntRangeKeyScope("$key.damage").apply(block)
  }
}

class EffectBuilder(private val key: String) : Builder() {

  fun type(value: String) {
    components += StringValue("$key.type:$value")
  }

  fun type(vararg values: String) {
    components += StringKeyScope("$key.type").apply {
      isIn(values.toList())
    }
  }

  fun type(value: Type) {
    type(value.displayName)
  }

  fun type(vararg values: Type) {
    type(values.toList())
  }

  fun type(values: Iterable<Type>) {
    components += StringKeyScope("$key.type").apply {
      isIn(values.map { it.displayName })
    }
  }

  fun type(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.type").apply(block)
  }

  fun value(value: String) {
    components += StringValue("$key.value:$value")
  }

  fun value(vararg values: String) {
    components += StringKeyScope("$key.value").apply {
      isIn(values.toList())
    }
  }

  fun value(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope("$key.value").apply(block)
  }
}
