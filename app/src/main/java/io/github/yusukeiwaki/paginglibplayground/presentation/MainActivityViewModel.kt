package io.github.yusukeiwaki.paginglibplayground.presentation

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import io.github.yusukeiwaki.paginglibplayground.api.QiitaApiClient
import io.github.yusukeiwaki.paginglibplayground.extension.switchMap
import io.github.yusukeiwaki.paginglibplayground.paging.QiitaItemDataSourceFactory

class MainActivityViewModel : ViewModel() {
    private val qiitaItemDataSourceFactory = QiitaItemDataSourceFactory(QiitaApiClient())

    private val qiitaItemPagedList = LivePagedListBuilder(qiitaItemDataSourceFactory, 30).build()

    fun qiitaItemPagedList() = qiitaItemPagedList

    fun qiitaItemLoadingStateLiveData() = qiitaItemDataSourceFactory.sourceAsLiveData().switchMap { source ->
        source!!.loadingStateLiveData()
    }
}
