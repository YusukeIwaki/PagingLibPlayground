package io.github.yusukeiwaki.paginglibplayground.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.github.yusukeiwaki.paginglibplayground.api.QiitaApiService
import io.github.yusukeiwaki.paginglibplayground.model.QiitaItem

class QiitaItemDataSourceFactory(private val apiClient: QiitaApiService) : DataSource.Factory<Int, QiitaItem>() {

    private val sourceAsLiveData = MutableLiveData<QiitaItemDataSource>()

    override fun create(): DataSource<Int, QiitaItem> =
            QiitaItemDataSource(apiClient).also { sourceAsLiveData.postValue(it) }

    fun sourceAsLiveData(): LiveData<QiitaItemDataSource> = sourceAsLiveData
}
