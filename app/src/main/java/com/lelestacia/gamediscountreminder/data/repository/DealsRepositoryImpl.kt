package com.lelestacia.gamediscountreminder.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail.DealDetailDto
import com.lelestacia.gamediscountreminder.data.source.remote.paging.GameDealPaging
import com.lelestacia.gamediscountreminder.domain.repository.DealsRepository
import com.lelestacia.gamediscountreminder.util.DataState
import com.lelestacia.gamediscountreminder.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DealsRepositoryImpl @Inject constructor(
    private val endpoint: DealsEndpoint
) : DealsRepository {

    override fun getDeals(): Flow<PagingData<DealDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 60,
                prefetchDistance = 10,
                enablePlaceholders = false,
                initialLoadSize = 60
            ),
            pagingSourceFactory = {
                GameDealPaging(endpoint)
            }
        ).flow
    }

    override fun getDealByID(id: String): Flow<DataState<DealDetailDto>> {
        return flow<DataState<DealDetailDto>> {
            val apiResult = endpoint.getDealByID(id)
            emit(DataState.Success(apiResult))
        }.catch { error ->
            emit(DataState.Failed(UiText.UiMessage(error.message.orEmpty())))
        }.onStart {
            emit(DataState.Loading)
        }
    }
}