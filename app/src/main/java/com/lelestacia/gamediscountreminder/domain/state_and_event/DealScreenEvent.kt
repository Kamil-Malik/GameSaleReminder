package com.lelestacia.gamediscountreminder.domain.state_and_event

import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto

sealed class DealScreenEvent {
    data class OnGameDealClicked(val deal: GameDealDto) : DealScreenEvent()
    data object OnBottomSheetDiscarded: DealScreenEvent()
}
