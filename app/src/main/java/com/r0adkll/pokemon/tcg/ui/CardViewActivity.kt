package com.r0adkll.pokemon.tcg.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.r0adkll.pokemon.tcg.GlideApp
import com.r0adkll.pokemon.tcg.R
import com.r0adkll.pokemon.tcg.ui.component.BaseActivity
import kotlinx.android.synthetic.main.activity_card_view.*

class CardViewActivity : BaseActivity() {

    private lateinit var imageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)

        setSupportActionBar(appBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appBar.setNavigationOnClickListener { supportFinishAfterTransition() }

        imageUrl = intent.getStringExtra(EXTRA_IMAGE)
        GlideApp.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.pokemon_card_back)
            .into(photoView)
    }

    companion object {
        const val EXTRA_IMAGE = "com.r0adkll.pokemon.tcg.intent.EXTRA_IMAGE"

        fun createIntent(context: Context, imageUrl: String): Intent {
            val intent = Intent(context, CardViewActivity::class.java)
            intent.putExtra(EXTRA_IMAGE, imageUrl)
            return intent
        }
    }
}
