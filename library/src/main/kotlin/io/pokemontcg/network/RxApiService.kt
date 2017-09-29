package io.pokemontcg.network


import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import io.pokemontcg.model.SubTypes
import io.pokemontcg.model.SuperTypes
import io.pokemontcg.model.Types
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface RxApiService {

    @GET("cards")
    fun getCards(): Observable<List<Card>>


    @GET("cards")
    fun getCards(@QueryMap filters: Map<String, String?>): Observable<List<Card>>


    @GET("cards/{id}")
    fun getCard(@Path("id") id: String): Observable<Card>


    @GET("types")
    fun getTypes(): Observable<Types>


    @GET("supertypes")
    fun getSuperTypes(): Observable<SuperTypes>


    @GET("subtypes")
    fun getSubTypes(): Observable<SubTypes>


    @GET("sets")
    fun getSets(): Observable<List<CardSet>>


    @GET("sets")
    fun getSets(@QueryMap filters: Map<String, String?>): Observable<List<CardSet>>


    @GET("sets/{id}")
    fun getSet(@Path("id") id: String): Observable<CardSet>
}