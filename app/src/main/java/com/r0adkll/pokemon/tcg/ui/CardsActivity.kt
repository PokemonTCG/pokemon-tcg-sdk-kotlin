package com.r0adkll.pokemon.tcg.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ftinc.kit.adapter.BetterRecyclerAdapter
import com.r0adkll.pokemon.tcg.GlideApp
import com.r0adkll.pokemon.tcg.R
import com.r0adkll.pokemon.tcg.ui.component.BaseActivity
import com.r0adkll.pokemon.tcg.util.snackbar
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.model.SuperType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_cards.*
import timber.log.Timber


class CardsActivity : BaseActivity() {

    private lateinit var pokemon: Pokemon
    private lateinit var adapter: CardAdapter
    private lateinit var superType: SuperType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        val ordinal = intent.getIntExtra(EXTRA_SUPERTYPE, SuperType.POKEMON.ordinal)
        superType = SuperType.values()[ordinal]

        adapter = CardAdapter(layoutInflater)
        adapter.setOnItemClickListener { _, item, _ ->
            startActivity(CardViewActivity.createIntent(this, item.imageUrlHiRes))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = superType.displayName

        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter

        pokemon = Pokemon()
        loadCards()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * For Pokemon cards, we can load mew just for demonstration sake.
     * For trainers and energy, we don't want to limit the search results so just avoid passing a name.
     */
    private fun loadCards() {
        val pokemonName = if (superType == SuperType.POKEMON) "mew" else ""

        disposables += pokemon.card()
                .where {
                    name = pokemonName
                    supertype = superType.displayName
                }
                .observeAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.clear()
                    adapter.addAll(it)
                    adapter.notifyDataSetChanged()
                }, {
                    Timber.e(it, "Error loading cards for $superType")
                    snackbar(it.localizedMessage)
                })
    }



    class CardAdapter(
            val layoutInflater: LayoutInflater
    ) : BetterRecyclerAdapter<Card, CardViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
            val view = layoutInflater.inflate(R.layout.item_card, parent, false)
            return CardViewHolder(view)
        }


        override fun onBindViewHolder(vh: CardViewHolder, i: Int) {
            super.onBindViewHolder(vh, i)
            val item = items[i]
            vh.bind(item)
        }
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView as ImageView


        fun bind(card: Card) {
            GlideApp.with(itemView)
                    .load(card.imageUrlHiRes)
                    .placeholder(R.drawable.pokemon_card_back)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)

        }
    }


    companion object {
        const val EXTRA_SUPERTYPE = "com.r0adkll.pokemon.tcg.intent.EXTRA_SUPERTYPE"

        fun createIntent(context: Context, superType: SuperType): Intent {
            val intent = Intent(context, CardsActivity::class.java)
            intent.putExtra(EXTRA_SUPERTYPE, superType.ordinal)
            return intent
        }
    }
}