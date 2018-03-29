package io.github.yusukeiwaki.paginglibplayground.extension

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations

fun <X, Y> LiveData<X>.map(f: (X?) -> Y?): LiveData<Y> {
    return Transformations.map(this, f)
}

fun <X, Y> LiveData<X>.switchMap(f: (X?) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, f)
}