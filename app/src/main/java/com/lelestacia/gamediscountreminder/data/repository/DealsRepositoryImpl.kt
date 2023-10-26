package com.lelestacia.gamediscountreminder.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto
import com.lelestacia.gamediscountreminder.data.source.remote.paging.GameDealPaging
import com.lelestacia.gamediscountreminder.domain.repository.DealsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DealsRepositoryImpl @Inject constructor(
    private val endpoint: DealsEndpoint
) : DealsRepository {

    override fun getDeals(): Flow<PagingData<GameDealDto>> {
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
}