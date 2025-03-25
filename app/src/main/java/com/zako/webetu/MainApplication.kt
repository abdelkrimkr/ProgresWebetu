package com.zako.webetu

import android.app.Application
import com.zako.webetu.auth.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MainApplication)
            modules(
                appModule ,
                loginModule
            )
        }
    }

}