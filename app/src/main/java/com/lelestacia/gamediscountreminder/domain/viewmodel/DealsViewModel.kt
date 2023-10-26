package com.lelestacia.gamediscountreminder.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto
import com.lelestacia.gamediscountreminder.domain.repository.DealsRepository
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenEvent
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val repository: DealsRepository
) : ViewModel() {

    private val _dealScreenState: MutableStateFlow<DealScreenState> =
        MutableStateFlow(DealScreenState())
    val dealScreenState: StateFlow<DealScreenState> =
        _dealScreenState.asStateFlow()

    val deals: Flow<PagingData<GameDealDto>> = repository
        .getDeals()
        .cachedIn(viewModelScope)

    fun onEvent(event: DealScreenEvent) {
        when (event) {
            is DealScreenEvent.OnGameDealClicked -> {
                _dealScreenState.update {
                    it.copy(
                        isBottomSheetOpened = true,
                        selectedDeals = event.deal
                    )
                }
            }

            DealScreenEvent.OnBottomSheetDiscarded -> {
                _dealScreenState.update {
                    it.copy(
                        isBottomSheetOpened = false,
                        selectedDeals = null
                    )
                }
            }
        }
    }
}