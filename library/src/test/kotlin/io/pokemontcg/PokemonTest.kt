package io.pokemontcg

import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.log

class PokemonTest {

    lateinit var pokemon: Pokemon

    @Before
    @Throws(Exception::class)
    fun setUp() {
        pokemon = Pokemon(Config(logLevel = HttpLoggingInterceptor.Level.NONE))
    }

    @Test
    fun `test all models parse correctly`() {
        val sets = pokemon.set().all()
        sets.forEach { set ->
            println("Fetching cards for ${set.code}:${set.name}")
            pokemon.card()
                    .where {
                        setCode = set.code
                        pageSize = 1000
                    }
                    .all()
        }
    }
}