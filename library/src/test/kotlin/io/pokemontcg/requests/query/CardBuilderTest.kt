package io.pokemontcg.requests.query

import io.pokemontcg.model.Legality
import io.pokemontcg.model.Type
import org.junit.Test

class CardBuilderTest {

  @Test
  fun testBuilder() {
    val builder = CardBuilder()

    with(builder) {
      id("sm1-100")
      id("sm1-100", "sm2-142", "sm8-299")
      ids(listOf(
        "sm10-100",
        "sm11-200",
        "swsh01-100",
      ))
      name("charizard")
      name("char*ard")
      name("*zard*")
      name {
        not("charizard")
        exact("charizard")
        "squirtle" or "bulbasaur" or "charmander"
        isIn(listOf(
          "charizard",
          "charizard ex",
          "charizard gx",
          "charizard vmaxx",
        ))
      }

      hp("*")
      hp(200)
      hp {
        lt(100)
        lte(150)
        gt(100)
        gte(150)
        between(50, 100)
        between(50, 100, inclusive = false)
        between(0..200)
        between(20..2000, inclusive = false)
      }
      abilities {
        name("Punch")
        name {
          "Kick" or "Punch" or "Stab"
          isIn(listOf("Kick", "Punch", "Stab"))
          isIn("Kick", "Punch", "Stab")
        }
        type("water")
        type {
          not("Grass")
        }
      }

      attacks {
        cost(Type.WATER, Type.GRASS)
        damage(500)
        damage("200+")
        damage {
          gt(500)
        }
      }

      abilities {
        name("Juxtapose")
        type {
          not("*Power")
        }
        text("Bacon")
      }

      weaknesses {
        type(Type.DARKNESS)
        value("-20")
      }

      resistances {
        type(Type.METAL)
      }

      set {
        id("sm1")
        name("Sun & Moon")
        legalities {
          unlimited(Legality.ILLEGAL)
          standard(Legality.LEGAL)
          expanded(Legality.BANNED)
        }
        printedTotal(500)
      }
    }


    val result = builder.build()

    println(result)
  }
}

