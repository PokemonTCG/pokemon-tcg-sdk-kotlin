package com.r0adkll.pokemon.tcg


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.pokemontcg.Pokemon
import io.pokemontcg.util.and
import io.pokemontcg.util.gt
import io.pokemontcg.util.or


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Pokemon.card()
                .where(name = "Charizard" or "Blastoise" or "Venasaur",
                        hp = 80.gt(),
                        attackDamage = "x10",
                        rarity = "Rare" and "Rare Holo" and "Common"
                        )
                .observeAll()

        Pokemon.card()
                .where(page = 5, pageSize = 500)
                .observeAll()

        Pokemon.set()
                .where(name = "Burning Shadows")
                .all()

        Pokemon.set()
                .find("0")

        Pokemon.type()
                .all()


    }
}
