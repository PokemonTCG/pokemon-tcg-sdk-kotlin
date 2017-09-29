package com.r0adkll.pokemon.tcg


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.ftinc.kit.adapter.BetterRecyclerAdapter
import io.pokemontcg.model.Card
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)

    }

    class CardAdapter(
            context: Context
    ) : BetterRecyclerAdapter<Card, > {

    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(card: Card) {
        }
    }
}
