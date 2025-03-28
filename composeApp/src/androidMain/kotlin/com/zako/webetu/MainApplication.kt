package com.zako.webetu

import android.app.Application
import com.zako.webetu.auth.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.zako.webetu.di.initKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@MainApplication)
        }
    }
}
