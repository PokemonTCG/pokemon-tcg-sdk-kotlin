package io.pokemontcg.requests

class QueryBuilder(
  var query: String = "",
  var page: Int = 1,
  var pageSize: Int = 250,
  var orderBy: List<String>? = null
) {

  fun toParams(): Map<String, String?> = mapOf(
    "q" to query,
    "page" to page.toString(),
    "pageSize" to pageSize.coerceIn(1..250).toString(),
    "orderBy" to orderBy?.joinToString(),
  )
}
