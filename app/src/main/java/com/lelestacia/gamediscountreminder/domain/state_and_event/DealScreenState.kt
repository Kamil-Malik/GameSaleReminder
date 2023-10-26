package com.lelestacia.gamediscountreminder.domain.state_and_event

import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto

data class DealScreenState(
    val isBottomSheetOpened: Boolean = false,
    val selectedDeals: GameDealDto? = null,
)