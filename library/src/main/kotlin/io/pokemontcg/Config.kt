package io.pokemontcg


import okhttp3.logging.HttpLoggingInterceptor


class Config(
        val apiUrl: String = Pokemon.DEFAULT_API_URL,
        val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.HEADERS
)