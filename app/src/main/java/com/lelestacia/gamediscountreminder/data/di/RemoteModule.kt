package com.lelestacia.gamediscountreminder.data.di

import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
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
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        interceptor: HttpLoggingInterceptor
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient()
                    .newBuilder()
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
}