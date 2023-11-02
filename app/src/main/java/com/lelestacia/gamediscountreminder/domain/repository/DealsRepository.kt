package com.lelestacia.gamediscountreminder.domain.repository

import androidx.paging.PagingData
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail.DealDetailDto
import com.lelestacia.gamediscountreminder.util.DataState
import kotlinx.coroutines.flow.Flow

interface DealsRepository {
    fun getDeals(): Flow<PagingData<DealDto>>
    fun getDealByID(id: String): Flow<DataState<DealDetailDto>>
}