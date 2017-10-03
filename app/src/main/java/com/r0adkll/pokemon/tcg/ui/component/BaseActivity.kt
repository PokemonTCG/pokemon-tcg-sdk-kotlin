package com.r0adkll.pokemon.tcg.ui.component


import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseActivity : AppCompatActivity() {

    protected val disposables: CompositeDisposable = CompositeDisposable()


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }


    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }
}