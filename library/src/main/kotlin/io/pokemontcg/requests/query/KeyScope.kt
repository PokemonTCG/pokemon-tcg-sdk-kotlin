package io.pokemontcg.requests.query

abstract class KeyScope(
  val key: String
) : QueryComponent {

  protected val components = mutableListOf<QueryComponent>()

  override fun build(): String {
    return components.joinToString(" ") { it.build() }
  }
}

class StringKeyScope(key: String) : KeyScope(key) {

  fun not(value: String) {
    components += NotParameter(QueryParameter(this, StringValue(value)))
  }

  fun exact(value: String) {
    components += ExactParameter(QueryParameter(this, StringValue(value)))
  }

  fun isIn(values: Iterable<String>) {
    components += LogicalParameter(
      values.map {
        QueryParameter(this, StringValue(it))
      }
    )
  }

  fun isIn(vararg values: String) {
    components += LogicalParameter(
      values.map {
        QueryParameter(this, StringValue(it))
      }
    )
  }

  infix fun String.or(other: String): LogicalParameter {
    return LogicalParameter(
      listOf(
        QueryParameter(this@StringKeyScope, StringValue(this)),
        QueryParameter(this@StringKeyScope, StringValue(other)),
      )
    ).also {
      components += it
    }
  }

  infix fun LogicalParameter.or(other: String): LogicalParameter {
    components.remove(this)
    return this
      .copy(clauses = this.clauses.plus(QueryParameter(this@StringKeyScope, StringValue(other))))
      .also {
        components += it
      }
  }
}

class IntRangeKeyScope(key: String) : KeyScope(key) {

  fun between(from: Int, to: Int, inclusive: Boolean = true) {
    components += QueryParameter(this, RangeValue(from.toString(), to.toString(), inclusive))
  }

  fun between(range: IntRange, inclusive: Boolean = true) {
    components += QueryParameter(this, RangeValue(range, inclusive))
  }

  fun gt(value: Int) {
    components += QueryParameter(
      this, RangeValue(
        from = value.toString(),
        to = "*",
        inclusive = false,
      )
    )
  }

  fun gte(value: Int) {
    components += QueryParameter(
      this, RangeValue(
        from = value.toString(),
        to = "*",
        inclusive = true,
      )
    )
  }

  fun lt(value: Int) {
    components += QueryParameter(
      this, RangeValue(
        from = "*",
        to = value.toString(),
        inclusive = false,
      )
    )
  }

  fun lte(value: Int) {
    components += QueryParameter(
      this, RangeValue(
        from = "*",
        to = value.toString(),
        inclusive = true,
      )
    )
  }


}
