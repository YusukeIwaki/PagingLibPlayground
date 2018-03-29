package io.github.yusukeiwaki.paginglibplayground.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import io.github.yusukeiwaki.paginglibplayground.api.QiitaApiService
import io.github.yusukeiwaki.paginglibplayground.model.QiitaItem
import kotlinx.coroutines.experimental.launch

class QiitaItemDataSource(private val apiClient: QiitaApiService) : PageKeyedDataSource<Int, QiitaItem>() {
    private val loadingStateLiveData = MutableLiveData<LoadingState>()

    private fun getItems(page: Int, perPage: Int, process: (List<QiitaItem>, Int?, Int?) -> Unit) {
        launch {
            loadingStateLiveData.postValue(LoadingState.ofLoading())
            try {
                val response = apiClient.getItems(page, perPage).await()
                var prev: Int? = null
                var next: Int? = null
                response.headers().get("Link")?.let { header ->
                    if (header.contains("rel=\"prev\"")) {
                        prev = page - 1
                    }
                    if (header.contains("rel=\"next\"")) {
                        next = page + 1
                    }
                }
                response.body()?.let { process(it, prev, next) }
                loadingStateLiveData.postValue(LoadingState.ofSuccess())
            } catch (e: Exception) {
                loadingStateLiveData.postValue(LoadingState.ofFailure(e))
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, QiitaItem>) {
        getItems(1, params.requestedLoadSize) { items, prev, next ->
            callback.onResult(items, prev, next)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QiitaItem>) {
        getItems(params.key, params.requestedLoadSize) { items, prev, next ->
            callback.onResult(items, next)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, QiitaItem>) {
        getItems(params.key, params.requestedLoadSize) { items, prev, next ->
            callback.onResult(items, prev)
        }
    }

    fun loadingStateLiveData(): LiveData<LoadingState> = loadingStateLiveData
}
