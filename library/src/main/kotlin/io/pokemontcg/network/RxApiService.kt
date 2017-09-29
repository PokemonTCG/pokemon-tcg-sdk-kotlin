package io.pokemontcg.network


import io.pokemontcg.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface RxApiService {

    @GET("cards")
    fun getCards(): Observable<CardResponse>


    @GET("cards")
    fun getCards(@QueryMap filters: Map<String, String?>): Observable<CardResponse>


    @GET("cards/{id}")
    fun getCard(@Path("id") id: String): Observable<Card>


    @GET("types")
    fun getTypes(): Observable<Types>


    @GET("supertypes")
    fun getSuperTypes(): Observable<SuperTypes>


    @GET("subtypes")
    fun getSubTypes(): Observable<SubTypes>


    @GET("sets")
    fun getSets(): Observable<SetResponse>


    @GET("sets")
    fun getSets(@QueryMap filters: Map<String, String?>): Observable<SetResponse>


    @GET("sets/{id}")
    fun getSet(@Path("id") id: String): Observable<CardSet>
}