package io.pokemontcg


import okhttp3.logging.HttpLoggingInterceptor


class Config(
        var apiUrl: String = Pokemon.DEFAULT_API_URL,
        var logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.HEADERS
)