package com.zako.webetu

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    single<HttpClient>{
        HttpClient(OkHttp){
            install(ContentNegotiation){
                json()
            }
        }
    }
}