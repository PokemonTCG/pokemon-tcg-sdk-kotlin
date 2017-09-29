package io.pokemontcg.network

import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import io.pokemontcg.model.SubTypes
import io.pokemontcg.model.SuperTypes
import io.pokemontcg.model.Types
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface SyncApiService {

    @GET("cards")
    fun getCards(): Call<List<Card>>


    @GET("cards")
    fun getCards(@QueryMap filters: Map<String, String?>): Call<List<Card>>


    @GET("cards/{id}")
    fun getCard(@Path("id") id: String): Call<Card>


    @GET("types")
    fun getTypes(): Call<Types>


    @GET("supertypes")
    fun getSuperTypes(): Call<SuperTypes>


    @GET("subtypes")
    fun getSubTypes(): Call<SubTypes>


    @GET("sets")
    fun getSets(): Call<List<CardSet>>


    @GET("sets")
    fun getSets(@QueryMap filters: Map<String, String?>): Call<List<CardSet>>


    @GET("sets/{id}")
    fun getSet(@Path("id") id: String): Call<CardSet>
}