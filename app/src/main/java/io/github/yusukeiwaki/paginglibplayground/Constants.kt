package io.github.yusukeiwaki.paginglibplayground

import android.content.Context

object Constants {
    lateinit var QIITA_API_TOKEN: String

    fun init(context: Context) {
        QIITA_API_TOKEN = context.getString(R.string.qiita_api_token)
    }
}
