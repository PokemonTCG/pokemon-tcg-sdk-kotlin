package io.pokemontcg.requests.query

import io.pokemontcg.model.Legality

class LegalitiesScope(
  key: String
) : KeyScope(key) {

  private fun key(field: String): String {
    return if (key.isEmpty()) field else "$key.$field"
  }

  fun unlimited(legality: Legality) {
    components += StringValue("${key("unlimited")}:${legality.name.lowercase()}")
  }

  fun standard(legality: Legality) {
    components += StringValue("${key("standard")}:${legality.name.lowercase()}")
  }

  fun expanded(legality: Legality) {
    components += StringValue("${key("expanded")}:${legality.name.lowercase()}")
  }
}
