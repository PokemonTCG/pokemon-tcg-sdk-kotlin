package io.pokemontcg


import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import io.pokemontcg.network.RxApiService
import io.pokemontcg.network.SyncApiService
import io.pokemontcg.util.result
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Root API object for interfacing with the io.pokemontcg.com API
 */
object Pokemon {

    private const val API_URL = "https://api.pokemontcg.io/v1"


    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }


    private val syncService: SyncApiService by lazy {
        val retroFit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retroFit.create(SyncApiService::class.java)
    }


    private val rxService: RxApiService by lazy {
        val retroFit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retroFit.create(RxApiService::class.java)
    }


    fun card(): CardBuilder = CardBuilder()
    fun set(): SetBuilder = SetBuilder()
    fun type(): TypeBuilder = TypeBuilder()
    fun superType(): SuperTypesBuilder = SuperTypesBuilder()
    fun subType(): SubTypesBuilder = SubTypesBuilder()


    /**
     * Helper class to build a query
     */
    class CardBuilder {

        fun where(name: String? = null,
                  nationalPokedexNumber: Int? = null,
                  types: String? = null,
                  subtype: String? = null,
                  supertype: String? = null,
                  hp: String? = null,
                  number: String? = null,
                  artist: String? = null,
                  rarity: String? = null,
                  series: String? = null,
                  set: String? = null,
                  setCode: String? = null,
                  retreatCost: String? = null,
                  text: String? = null,
                  attackDamage: String? = null,
                  attackCost: String? = null,
                  attackName: String? = null,
                  attackText: String? = null,
                  weaknesses: String? = null,
                  resistances: String? = null,
                  ancientTrait: String? = null,
                  abilityName: String? = null,
                  abilityText: String? = null,
                  abilityType: String? = null,
                  contains: String? = null,
                  page: Int? = null,
                  pageSize: Int? = null): Where {

            return Where(mapOf(
                    "name" to name,
                    "nationalPokedexNumber" to nationalPokedexNumber?.toString(),
                    "types" to types,
                    "subtype" to subtype,
                    "supertype" to supertype,
                    "hp" to hp,
                    "number" to number,
                    "artist" to artist,
                    "rarity" to rarity,
                    "series" to series,
                    "set" to set,
                    "setCode" to setCode,
                    "retreatCost" to retreatCost,
                    "text" to text,
                    "attackDamage" to attackDamage,
                    "attackCost" to attackCost,
                    "attackName" to attackName,
                    "attackText" to attackText,
                    "weaknesses" to weaknesses,
                    "resistances" to resistances,
                    "ancientTrait" to ancientTrait,
                    "abilityName" to abilityName,
                    "abilityText" to abilityText,
                    "abilityType" to abilityType,
                    "contains" to contains,
                    "page" to page?.toString(),
                    "pageSize" to pageSize?.toString()))
        }


        class Where(private val params: Map<String, String?>) {

            fun all(): List<Card> = syncService.getCards(params).result()
            fun observeAll(): Observable<List<Card>> = rxService.getCards(params)
        }

        fun all(): List<Card> = syncService.getCards().result()
        fun observeAll(): Observable<List<Card>> = rxService.getCards()
        fun find(id: String): Card = syncService.getCard(id).result()
        fun observeFind(id: String): Observable<Card> = rxService.getCard(id)
    }


    /**
     * Helper class to assemble Set queries
     */
    class SetBuilder {

        fun where(name: String? = null,
                  series: String? = null,
                  totalCards: String? = null,
                  standardLegal: Boolean? = null,
                  expandedLegal: Boolean? = null): Where {
            return Where(mapOf(
                    "name" to name,
                    "series" to series,
                    "totalCards" to totalCards,
                    "standardLegal" to standardLegal.toString(),
                    "expandedLegal" to expandedLegal.toString()
            ))
        }

        class Where(val params: Map<String, String?>) {

            fun all(): List<CardSet> = syncService.getSets(params).result()
            fun observeAll(): Observable<List<CardSet>> = rxService.getSets(params)
        }

        fun all(): List<CardSet> = syncService.getSets().result()
        fun observeAll(): Observable<List<CardSet>> = rxService.getSets()
        fun find(id: String): CardSet = syncService.getSet(id).result()
        fun observeFind(id: String): Observable<CardSet> = rxService.getSet(id)
    }


    class TypeBuilder {

        fun all(): List<String> = syncService.getTypes().result().types
        fun observeAll(): Observable<List<String>> = rxService.getTypes().map { it.types }
    }


    class SuperTypesBuilder {

        fun all(): List<String> = syncService.getSuperTypes().result().supertypes
        fun observeAll(): Observable<List<String>> = rxService.getSuperTypes().map { it.supertypes }
    }


    class SubTypesBuilder {

        fun all(): List<String> = syncService.getSubTypes().result().subtypes
        fun observeAll(): Observable<List<String>> = rxService.getSubTypes().map { it.subtypes }
    }
}