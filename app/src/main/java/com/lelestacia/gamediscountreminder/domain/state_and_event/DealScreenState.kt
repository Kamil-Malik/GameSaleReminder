package com.lelestacia.gamediscountreminder.domain.state_and_event

import com.lelestacia.gamediscountreminder.data.source.local.entity.StoreEntity
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail.DealDetailDto
import com.lelestacia.gamediscountreminder.util.DataState

data class DealScreenState(
    val isBottomSheetOpened: Boolean = false,
    val selectedDeal: DealDto? = null,
    val store: List<StoreEntity> = emptyList(),
    val selectedDealDetail: DataState<DealDetailDto> = DataState.None
) {
    data class Detail(
        val dealDetail: DealDetailDto? = null,
        val isDetailLoading: Boolean = false
    )
}