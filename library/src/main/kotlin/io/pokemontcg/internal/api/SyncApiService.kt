package io.pokemontcg.internal.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

internal interface SyncApiService {

    @GET("cards")
    fun getCards(): Call<CardResponse>

    @GET("cards")
    fun getCards(@QueryMap filters: Map<String, String?>): Call<CardResponse>

    @GET("cards/{id}")
    fun getCard(@Path("id") id: String): Call<CardModel>

    @GET("types")
    fun getTypes(): Call<TypeResponse>

    @GET("supertypes")
    fun getSuperTypes(): Call<SuperTypeResponse>

    @GET("subtypes")
    fun getSubTypes(): Call<SubTypeResponse>

    @GET("sets")
    fun getSets(): Call<SetResponse>

    @GET("sets")
    fun getSets(@QueryMap filters: Map<String, String?>): Call<SetResponse>

    @GET("sets/{id}")
    fun getSet(@Path("id") id: String): Call<CardSetModel>
}
