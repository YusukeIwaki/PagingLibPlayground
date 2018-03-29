package io.github.yusukeiwaki.paginglibplayground.api

import io.github.yusukeiwaki.paginglibplayground.model.QiitaItem
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApiService {
    @GET("/api/v2/items")
    fun getItems(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 20): Deferred<Response<List<QiitaItem>>>
}
