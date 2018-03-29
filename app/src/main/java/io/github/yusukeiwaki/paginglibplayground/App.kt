package io.github.yusukeiwaki.paginglibplayground

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Constants.init(this)
    }
}
