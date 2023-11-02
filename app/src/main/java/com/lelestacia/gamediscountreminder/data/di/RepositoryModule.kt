package com.lelestacia.gamediscountreminder.data.di

import com.lelestacia.gamediscountreminder.data.repository.DealsRepositoryImpl
import com.lelestacia.gamediscountreminder.data.repository.StoreRepositoryImpl
import com.lelestacia.gamediscountreminder.data.source.local.dao.StoreDao
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.api.StoresEndpoint
import com.lelestacia.gamediscountreminder.domain.repository.DealsRepository
import com.lelestacia.gamediscountreminder.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
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

    @Provides
    @Singleton
    fun provideStoreRepository(
        endpoint: StoresEndpoint,
        dao: StoreDao
    ): StoreRepository {
        return StoreRepositoryImpl(
            endpoint = endpoint,
            localDataSource = dao,
            ioDispatcher = Dispatchers.IO
        )
    }
}