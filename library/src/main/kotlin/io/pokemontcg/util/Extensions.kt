package io.pokemontcg.util


import retrofit2.Call
import java.io.IOException


fun Int.gt(): String = "gt$this"
fun Int.gte(): String = "gte$this"
fun Int.lt(): String = "lt$this"
fun Int.lte(): String = "lte$this"

infix fun String.and(value: String): String = "$this,$value"
infix fun String.or(value: String): String = "$this|$value"

fun <T> Call<T>.result(): T {
    return execute().let {
        if (it.isSuccessful) it.body()!! else throw IOException("[${it.code()}]: ${it.message()}")
    }
}