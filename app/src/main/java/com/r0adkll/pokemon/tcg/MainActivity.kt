package com.r0adkll.pokemon.tcg


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ftinc.kit.adapter.BetterRecyclerAdapter
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var adapter: CardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CardAdapter(layoutInflater)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        Pokemon.card()
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
                    .into(image)

        }
    }
}
