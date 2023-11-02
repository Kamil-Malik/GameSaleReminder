package com.lelestacia.gamediscountreminder.data.di

import com.lelestacia.gamediscountreminder.BuildConfig
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.api.StoresEndpoint
import com.lelestacia.gamediscountreminder.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        interceptor: HttpLoggingInterceptor
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient()
                    .newBuilder()
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .addInterceptor(interceptor)
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideDealsEndpoint(
        retrofit: Retrofit
    ): DealsEndpoint {
        return retrofit.create(DealsEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideStoresEndpoint(
        retrofit: Retrofit
    ): StoresEndpoint {
        return retrofit.create(StoresEndpoint::class.java)
    }
}