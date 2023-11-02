package com.lelestacia.gamediscountreminder.domain.repository

import com.lelestacia.gamediscountreminder.data.source.local.entity.StoreEntity
import com.lelestacia.gamediscountreminder.util.DataState
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    fun setupStore(): Flow<DataState<Boolean>>
    suspend fun getAllStore(): List<StoreEntity>
}