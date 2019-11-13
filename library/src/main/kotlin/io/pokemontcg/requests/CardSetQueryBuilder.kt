package io.pokemontcg.requests

class CardSetQueryBuilder(
    var name: String? = null,
    var series: String? = null,
    var totalCards: String? = null,
    var standardLegal: Boolean? = null,
    var expandedLegal: Boolean? = null,
    var pageSize: Int? = null
) : QueryBuilder {

    override fun toParams(): Map<String, String?> = mapOf(
        "name" to name,
        "series" to series,
        "totalCards" to totalCards,
        "standardLegal" to standardLegal?.toString(),
        "expandedLegal" to expandedLegal?.toString(),
        "pageSize" to pageSize?.toString()
    )
}
