package io.pokemontcg

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

class PokemonTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    lateinit var pokemon: Pokemon

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        pokemon = Pokemon(Config(logLevel = HttpLoggingInterceptor.Level.NONE))
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
                println("Fetching cards for ${set.code}:${set.name}")
                pokemon.card()
                    .where {
                        setCode = set.code
                        pageSize = 1000
                    }
                    .all()

                // This is needed to avoid rate-limits in the api
                delay(2000L)
            }
        }
    }
}
