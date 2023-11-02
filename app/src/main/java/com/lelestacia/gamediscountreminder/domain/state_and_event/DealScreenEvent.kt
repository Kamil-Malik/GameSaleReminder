package com.lelestacia.gamediscountreminder.domain.state_and_event

import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto

sealed class DealScreenEvent {
    data class OnGameDealClicked(val deal: DealDto) : DealScreenEvent()
    data object OnBottomSheetDiscarded: DealScreenEvent()
}
