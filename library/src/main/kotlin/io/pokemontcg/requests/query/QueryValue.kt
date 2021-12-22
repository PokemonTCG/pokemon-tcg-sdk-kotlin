package io.pokemontcg.requests.query

sealed interface QueryValue : QueryComponent

class StringValue(private val value: String) : QueryValue {

  override fun build(): String {
    return value
  }
}

class RangeValue(
  private val from: String,
  private val to: String,
  private val inclusive: Boolean = true
) : QueryValue {

  constructor(range: IntRange, inclusive: Boolean) : this(
    from = range.first.toString(),
    to = range.last.toString(),
    inclusive = inclusive,
  )

  override fun build(): String {
    return if (inclusive) {
      "[$from TO $to]"
    } else {
      "{$from TO $to}"
    }
  }
}
