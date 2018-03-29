package io.github.yusukeiwaki.paginglibplayground.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import io.github.yusukeiwaki.paginglibplayground.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class QiitaApiClient : QiitaApiService by qiitaApiService {
    companion object {
        private val authHeaderInterceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                    .header("Authorization", "Bearer ${Constants.QIITA_API_TOKEN}")
                    .build()

            chain.proceed(newRequest)
        }

        private val client = OkHttpClient().newBuilder()
                .addInterceptor(authHeaderInterceptor)
                .build()

        private val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()

        private val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        private val qiitaApiService = retrofit.create(QiitaApiService::class.java)
    }
}
