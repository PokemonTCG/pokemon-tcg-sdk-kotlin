package io.pokemontcg

import io.pokemontcg.requests.Request
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.rx2.rxSingle

fun <T> Request<T>.allAsSingle(): Single<List<T>> {
  return rxSingle {
    all()
  }
}

fun <T> Request<T>.allAsObservable(): Observable<List<T>> {
  return allAsSingle().toObservable()
}
