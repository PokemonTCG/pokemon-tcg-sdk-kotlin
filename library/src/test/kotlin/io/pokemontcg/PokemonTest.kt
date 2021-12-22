package io.pokemontcg

import io.pokemontcg.requests.query.CardBuilder
import io.pokemontcg.requests.query.cardBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalPokemonApi
class PokemonTest {

  private val mainThreadSurrogate = newSingleThreadContext("UI thread")
  lateinit var pokemon: Pokemon

  @Before
  fun setUp() {
    Dispatchers.setMain(mainThreadSurrogate)
    pokemon = Pokemon(Config(
      logLevel = HttpLoggingInterceptor.Level.NONE,
      apiKey = "add-api-key-here",
    ))
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    mainThreadSurrogate.close()
  }

  @ExperimentalCoroutinesApi
  @Test
  fun `test all models parse correctly`() {
    runBlocking {
      val sets = pokemon.set().all()
      sets.forEach { set ->
        println("Fetching cards for ${set.id}:${set.name}")
        pokemon.card()
          .where {
            query = cardBuilder {
              set {
                id(set.id)
              }
            }
            pageSize = 1000
          }
          .all()

        // This is needed to avoid rate-limits in the api
        delay(2000L)
      }
    }
  }
}
