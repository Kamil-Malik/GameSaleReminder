package com.lelestacia.gamediscountreminder.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.domain.repository.DealsRepository
import com.lelestacia.gamediscountreminder.domain.repository.StoreRepository
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenEvent
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenState
import com.lelestacia.gamediscountreminder.util.DataState
import com.lelestacia.gamediscountreminder.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val dealsRepository: DealsRepository,
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _dealScreenState: MutableStateFlow<DealScreenState> =
        MutableStateFlow(DealScreenState())
    val dealScreenState: StateFlow<DealScreenState> =
        _dealScreenState.asStateFlow()

    val deals: Flow<PagingData<DealDto>> = dealsRepository
        .getDeals()
        .cachedIn(viewModelScope)

    fun onEvent(event: DealScreenEvent) {
        when (event) {
            is DealScreenEvent.OnGameDealClicked -> {
                _dealScreenState.update { state ->
                    state.copy(
                        isBottomSheetOpened = true,
                        selectedDeal = event.deal
                    )
                }

                getDealDetail(event.deal.dealID)
            }

            DealScreenEvent.OnBottomSheetDiscarded -> {
                _dealScreenState.update { state ->
                    state.copy(
                        isBottomSheetOpened = false,
                        selectedDeal = null,
                        selectedDealDetail = DataState.None
                    )
                }
            }
        }
    }

    private fun getDealDetail(id: String) = viewModelScope.launch {
        Timber.d("Getting Deals with id $id")
        val decodedID = URLDecoder.decode(id, StandardCharsets.UTF_8.toString())
        dealsRepository.getDealByID(id = decodedID).collectLatest { result ->

            when (result) {
                is DataState.Failed -> {
                    when(result.error) {
                        is UiText.UiMessage -> Timber.w("Error : ${result.error.message}")
                        is UiText.UiResource -> Unit
                    }
                }
                is DataState.Success -> Timber.d("Deals Lookup : ${result.data}")
                else -> Unit
            }

            _dealScreenState.update { state ->
                state.copy(
                    selectedDealDetail = result
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            _dealScreenState.update { state ->
                state.copy(
                    store = storeRepository.getAllStore()
                )
            }
        }
    }
}