package com.zako.webetu.auth.login.di



import com.zako.webetu.auth.login.data.remote.RemoteDataSourceImpl
import com.zako.webetu.auth.login.model.RemoteDataSource
import com.zako.webetu.auth.login.presentation.LoginScreenViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginScreenViewModel)
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}