package io.pokemontcg

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.pokemontcg.internal.api.ModelMapper
import io.pokemontcg.internal.api.ApiService
import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import io.pokemontcg.model.SuperType
import io.pokemontcg.model.Type
import io.pokemontcg.requests.*
import io.pokemontcg.util.resultAs
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Root API object for interfacing with the io.pokemontcg.com API
 */
@Suppress("JoinDeclarationAndAssignment")
class Pokemon {

    companion object {
        const val DEFAULT_API_URL = "https://api.pokemontcg.io/v2/"
        const val API_KEY_HEADER = "X-Api-Key"
    }

    private val okHttpClient: OkHttpClient
    private val service: ApiService

    constructor(apiKey: String) : this(Config(apiKey))

    @OptIn(ExperimentalSerializationApi::class)
    constructor(config: Config) {
        val client = config.client
            ?: OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = config.logLevel
                })
                .build()

        okHttpClient = client.newBuilder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader(API_KEY_HEADER, config.apiKey)
                    .build()
                it.proceed(request)
            }
            .build()

        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        val retroFit = Retrofit.Builder()
            .baseUrl(config.apiUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        service = retroFit.create(ApiService::class.java)
    }

    fun card(): QueryRequest<Card> = CardBuilder()
    fun set(): QueryRequest<CardSet> = SetBuilder()
    fun type(): Request<Type> = TypeBuilder()
    fun superType(): Request<SuperType> = SuperTypesBuilder()
    fun subType(): Request<String> = SubTypesBuilder()

    /**
     * Helper class to build a query
     */
    private inner class CardBuilder : QueryRequest<Card> {

        override fun where(query: QueryBuilder): WhereRequest<Card> {
            return Where(query.toParams())
        }

        override fun where(query: QueryBuilder.() -> Unit): WhereRequest<Card> {
            val queryBuilder = QueryBuilder()
            queryBuilder.query()
            return Where(queryBuilder.toParams())
        }

        inner class Where(params: Map<String, String?>) : WhereRequest<Card>(params) {

            override suspend fun all(): List<Card> {
                return service.getCards(query)
                    .resultAs { ModelMapper.toCards(it.cards) }
            }
        }

        override suspend fun all(): List<Card> {
            return service.getCards()
                .resultAs { ModelMapper.toCards(it.cards) }
        }

        override suspend fun find(id: String): Card {
            return service.getCard(id)
                .resultAs { ModelMapper.to(it) }
        }
    }

    /**
     * Helper class to assemble Set queries
     */
    private inner class SetBuilder : QueryRequest<CardSet> {

        override fun where(query: QueryBuilder): WhereRequest<CardSet> {
            return Where(query.toParams())
        }

        override fun where(query: QueryBuilder.() -> Unit): WhereRequest<CardSet> {
            val queryBuilder = QueryBuilder()
            queryBuilder.query()
            return Where(queryBuilder.toParams())
        }

        inner class Where(params: Map<String, String?>) : WhereRequest<CardSet>(params) {

            override suspend fun all(): List<CardSet> {
                return service.getSets(query)
                    .resultAs { ModelMapper.toSets(it.sets) }
            }
        }

        override suspend fun all(): List<CardSet> {
            return service.getSets()
                .resultAs { ModelMapper.toSets(it.sets) }
        }

        override suspend fun find(id: String): CardSet {
            return service.getSet(id)
                .resultAs { ModelMapper.to(it) }
        }
    }

    private inner class TypeBuilder : Request<Type> {

        override suspend fun all(): List<Type> {
            return service.getTypes()
                .resultAs { response ->
                    response.data.map { Type.find(it) }
                }
        }
    }

    private inner class SuperTypesBuilder : Request<SuperType> {

        override suspend fun all(): List<SuperType> {
            return service.getSuperTypes()
                .resultAs { response ->
                    response.data.map { SuperType.find(it) }
                }
        }
    }

    private inner class SubTypesBuilder : Request<String> {

        override suspend fun all(): List<String> {
            return service.getSubTypes()
                .resultAs { it.data }
        }
    }
}
