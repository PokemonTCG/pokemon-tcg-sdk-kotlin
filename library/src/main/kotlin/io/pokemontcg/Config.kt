package io.pokemontcg

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class Config(
    val apiKey: String,
    val apiUrl: String = Pokemon.DEFAULT_API_URL,
    val client: OkHttpClient? = null,
    val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.HEADERS
)
