package io.pokemontcg.requests.query

/**
 * The root element that every node and action in the query build system must
 * implement.
 */
interface QueryComponent {

  /**
   * Required override where each component can define how it builds it's part of the query
   */
  fun build(): String
}
