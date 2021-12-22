package io.pokemontcg.util

import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun Int.gt(): String = "gt$this"
fun Int.gte(): String = "gte$this"
fun Int.lt(): String = "lt$this"
fun Int.lte(): String = "lte$this"

infix fun String.and(value: String): String = "$this,$value"
infix fun String.or(value: String): String = "$this|$value"

@Throws(HttpException::class)
fun <T, R> Response<T>.resultAs(mapper: (T) -> R): R {
    return if (isSuccessful) {
        body()?.let(mapper) ?: throw HttpException(this)
    } else {
        throw HttpException(this)
    }
}
