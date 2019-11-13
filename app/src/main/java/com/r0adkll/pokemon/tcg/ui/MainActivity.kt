package com.r0adkll.pokemon.tcg.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.r0adkll.pokemon.tcg.R
import io.pokemontcg.model.SuperType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        action_pokemon_cards.setOnClickListener {
            startActivity(CardsActivity.createIntent(this, SuperType.POKEMON))
        }

        action_trainer_cards.setOnClickListener {
            startActivity(CardsActivity.createIntent(this, SuperType.TRAINER))
        }

        action_energy_cards.setOnClickListener {
            startActivity(CardsActivity.createIntent(this, SuperType.ENERGY))
        }

        action_types.setOnClickListener {

        }

        action_super_types.setOnClickListener {

        }

        action_sub_types.setOnClickListener {

        }

    }
}
