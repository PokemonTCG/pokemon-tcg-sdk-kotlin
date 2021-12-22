package io.pokemontcg.requests

class CardQueryBuilder(
    var query: String = "",
    var page: Int = 1,
    var pageSize: Int = 250,
    var orderBy: List<String>? = null
) : QueryBuilder {

    override fun toParams(): Map<String, String?> = mapOf(
        "q" to buildQuery(),
        "page" to page.toString(),
        "pageSize" to pageSize.coerceIn(1..250).toString(),
        "orderBy" to orderBy?.joinToString(),
    )

    private fun buildQuery(): String {
        return query
    }
}
