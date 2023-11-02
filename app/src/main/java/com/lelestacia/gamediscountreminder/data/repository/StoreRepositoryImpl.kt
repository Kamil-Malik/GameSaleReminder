package com.lelestacia.gamediscountreminder.data.repository

import com.lelestacia.gamediscountreminder.data.mapper.toEntity
import com.lelestacia.gamediscountreminder.data.source.local.dao.StoreDao
import com.lelestacia.gamediscountreminder.data.source.local.entity.StoreEntity
import com.lelestacia.gamediscountreminder.data.source.remote.api.StoresEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.dto.store.StoreDto
import com.lelestacia.gamediscountreminder.domain.repository.StoreRepository
import com.lelestacia.gamediscountreminder.util.DataState
import com.lelestacia.gamediscountreminder.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class StoreRepositoryImpl @Inject constructor(
    private val endpoint: StoresEndpoint,
    private val localDataSource: StoreDao,
    private val ioDispatcher: CoroutineContext
) : StoreRepository {

    override fun setupStore(): Flow<DataState<Boolean>> {
        return flow<DataState<Boolean>> {
            val localStore: List<StoreEntity> = localDataSource.getAllStore()

            if (localStore.isEmpty()) {
                val apiResult: List<StoreDto> = endpoint.getStores()
                localDataSource.insertStores(apiResult.map(StoreDto::toEntity))
                emit(DataState.Success(true))
                return@flow
            }

            emit(DataState.Success(true))
        }.catch { error ->
            Timber.e(error)
            emit(DataState.Failed(UiText.UiMessage(error.message.orEmpty())))
        }.onStart {
            emit(DataState.Loading)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getAllStore(): List<StoreEntity> {
        return withContext(ioDispatcher) {
            localDataSource.getAllStore()
        }
    }
}