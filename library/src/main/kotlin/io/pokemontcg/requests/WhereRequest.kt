package io.pokemontcg.requests

abstract class WhereRequest<T>(
        params: Map<String, String?>
) : Request<T> {

    protected val query: Map<String, String?> = params.filterValues { it != null }
}
