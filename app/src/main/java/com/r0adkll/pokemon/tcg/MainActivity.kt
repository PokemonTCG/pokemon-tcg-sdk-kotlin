package com.r0adkll.pokemon.tcg


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ftinc.kit.adapter.BetterRecyclerAdapter
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.util.gt
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var adapter: CardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CardAdapter(layoutInflater)
        recycler.layoutManager = GridLayoutManager(this, 1)
        recycler.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycler)

        val pokemon = Pokemon()
        pokemon.card()
                .observeAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.clear()
                    adapter.addAll(it)
                    adapter.notifyDataSetChanged()
                }, {
                    it.printStackTrace()
                })

        pokemon.card()
                .where {
                    nationalPokedexNumber = 150
                    hp = 80.gt()
                }
                .observeAll()

        pokemon.set()
                .where {
                    standardLegal = true
                    expandedLegal = true
                }
                .observeAll()
    }

    class CardAdapter(
            val layoutInflater: LayoutInflater
    ) : BetterRecyclerAdapter<Card, CardViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardViewHolder {
            val view = layoutInflater.inflate(R.layout.item_card, parent, false)
            return CardViewHolder(view)
        }


        override fun onBindViewHolder(vh: CardViewHolder, i: Int) {
            val item = items[i]
            vh.bind(item)
        }
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView as ImageView


        fun bind(card: Card) {
            Glide.with(itemView)
                    .load(card.imageUrlHiRes)
                    .apply(RequestOptions.placeholderOf(R.drawable.pokemon_card_back))
                    .into(image)

        }
    }
}
