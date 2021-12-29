package io.pokemontcg.requests.query

import io.pokemontcg.ExperimentalPokemonApi

@ExperimentalPokemonApi
fun setBuilder(block: SetBuilder.() -> Unit): String {
  val builder = SetBuilder()
  builder.block()
  return builder.build()
}

@ExperimentalPokemonApi
class SetBuilder(
  key: String = ""
) : KeyScope(key) {

  private fun key(field: String): String {
    return if (key.isEmpty()) field else "$key.$field"
  }

  fun id(value: String) {
    components += StringValue("${key("id")}:$value")
  }

  fun id(vararg values: String) {
    ids(values.toList())
  }

  fun ids(values: Iterable<String>) {
    components += StringKeyScope(key("id")).apply {
      isIn(values)
    }
  }

  fun id(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope(key("id")).apply(block)
  }

  fun name(value: String) {
    components += StringValue("${key("name")}:$value")
  }

  fun name(vararg value: String) {
    names(value.toList())
  }

  fun names(values: Iterable<String>) {
    components += StringKeyScope(key("name")).apply {
      isIn(values)
    }
  }

  fun name(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope(key("name")).apply(block)
  }

  fun series(value: String) {
    components += StringValue("${key("series")}:$value")
  }

  fun series(vararg values: String) {
    series(values.toList())
  }

  fun series(values: Iterable<String>) {
    components += StringKeyScope(key("series")).apply {
      isIn(values)
    }
  }

  fun series(block: StringKeyScope.() -> Unit) {
    components += StringKeyScope(key("series")).apply(block)
  }

  fun printedTotal(value: Int) {
    components += StringValue("${key("printedTotal")}:$value")
  }

  fun printedTotal(block: IntRangeKeyScope.() -> Unit) {
    components += IntRangeKeyScope(key("printedTotal")).apply(block)
  }

  fun total(value: Int) {
    components += StringValue("${key("total")}:$value")
  }

  fun total(block: IntRangeKeyScope.() -> Unit) {
    components += IntRangeKeyScope(key("total")).apply(block)
  }

  fun legalities(block: LegalitiesScope.() -> Unit) {
    components += LegalitiesScope(key("legalities")).apply(block)
  }

  fun ptcgoCode(value: String) {
    components += StringValue("${key("ptcgoCode")}:$value")
  }

  fun releaseDate(value: String) {
    components += StringValue("${key("releaseDate")}:$value")
  }
}
