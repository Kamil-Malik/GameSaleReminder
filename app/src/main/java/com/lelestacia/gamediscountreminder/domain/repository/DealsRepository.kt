package com.lelestacia.gamediscountreminder.domain.repository

import androidx.paging.PagingData
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto
import kotlinx.coroutines.flow.Flow

interface DealsRepository {
    fun getDeals(): Flow<PagingData<GameDealDto>>
}