package com.lelestacia.gamediscountreminder.data.di

import com.lelestacia.gamediscountreminder.data.repository.DealsRepositoryImpl
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.domain.repository.DealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDealsRepository(
       endpoint: DealsEndpoint
    ): DealsRepository {
        return DealsRepositoryImpl(endpoint)
    }
}