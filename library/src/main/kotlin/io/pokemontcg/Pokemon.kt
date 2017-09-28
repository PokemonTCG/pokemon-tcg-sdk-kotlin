package io.pokemontcg

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Root API object for interfacing with the io.pokemontcg.com API
 */
object Pokemon {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

}