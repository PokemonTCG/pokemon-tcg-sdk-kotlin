package io.pokemontcg.internal.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

internal interface ApiService {

    @GET("cards")
    suspend fun getCards(): Response<CardResponse>

    @GET("cards")
    suspend fun getCards(@QueryMap filters: Map<String, String?>): Response<CardResponse>

    @GET("cards/{id}")
    suspend fun getCard(@Path("id") id: String): Response<CardModel>

    @GET("types")
    suspend fun getTypes(): Response<SimpleResponse>

    @GET("supertypes")
    suspend fun getSuperTypes(): Response<SimpleResponse>

    @GET("subtypes")
    suspend fun getSubTypes(): Response<SimpleResponse>

    @GET("sets")
    suspend fun getSets(): Response<SetResponse>

    @GET("sets")
    suspend fun getSets(@QueryMap filters: Map<String, String?>): Response<SetResponse>

    @GET("sets/{id}")
    suspend fun getSet(@Path("id") id: String): Response<CardSetModel>
}
