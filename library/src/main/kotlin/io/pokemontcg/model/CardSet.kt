package io.pokemontcg.model

data class CardSet(
  /**
   * Unique identifier for the object.
   */
  val id: String,

  /**
   * The name of the set.
   */
  val name: String,

  /**
   * The series the set belongs to, like Sword and Shield or Base.
   */
  val series: String,

  /**
   * The number printed on the card that represents the total.
   * This total does not include secret rares.
   */
  val printedTotal: Int,

  /**
   * The total number of cards in the set, including secret rares, alternate art, etc.
   */
  val total: Int,

  /**
   * The legalities of the set.
   */
  val legalities: Legalities,

  /**
   * The code the Pokémon Trading Card Game Online uses to identify a set.
   */
  val ptcgoCode: String?,

  /**
   * The date the set was released (in the USA). Format is YYYY/MM/DD.
   */
  val releaseDate: String,

  /**
   * The date and time the set was updated. Format is YYYY/MM/DD HH:MM:SS.
   */
  val updatedAt: String,

  /**
   * Any images associated with the set, such as symbol and logo.
   */
  val images: Images,
) {

  data class Images(
    val symbol: String,
    val logo: String,
  )
}

