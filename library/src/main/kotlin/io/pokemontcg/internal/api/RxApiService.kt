package io.pokemontcg.internal.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

internal interface RxApiService {

    @GET("cards")
    fun getCards(): Observable<CardResponse>

    @GET("cards")
    fun getCards(@QueryMap filters: Map<String, String?>): Observable<CardResponse>

    @GET("cards/{id}")
    fun getCard(@Path("id") id: String): Observable<CardModel>

    @GET("types")
    fun getTypes(): Observable<TypeResponse>

    @GET("supertypes")
    fun getSuperTypes(): Observable<SuperTypeResponse>

    @GET("subtypes")
    fun getSubTypes(): Observable<SubTypeResponse>

    @GET("sets")
    fun getSets(): Observable<SetResponse>

    @GET("sets")
    fun getSets(@QueryMap filters: Map<String, String?>): Observable<SetResponse>

    @GET("sets/{id}")
    fun getSet(@Path("id") id: String): Observable<CardSetModel>
}
