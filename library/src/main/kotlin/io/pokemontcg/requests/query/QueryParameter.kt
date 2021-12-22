package io.pokemontcg.requests.query

class QueryParameter(
  private val scope: KeyScope,
  private val value: QueryValue,
) : QueryComponent {

  override fun build(): String {
    return "${scope.key}:${value.build()}"
  }
}

class NotParameter(
  private val param: QueryParameter
) : QueryComponent {

  override fun build(): String {
    return "-${param.build()}"
  }
}

class ExactParameter(
  private val param: QueryParameter
) : QueryComponent {

  override fun build(): String {
    return "!${param.build()}"
  }
}

data class LogicalParameter(
  val clauses: List<QueryParameter>
) : QueryComponent {

  override fun build(): String {
    return if (clauses.size > 1) {
      "(${clauses.joinToString(" OR ") { it.build() }})"
    } else {
      clauses.firstOrNull()?.build() ?: ""
    }
  }
}
