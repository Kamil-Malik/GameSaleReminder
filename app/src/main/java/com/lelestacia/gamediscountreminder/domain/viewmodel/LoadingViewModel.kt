package com.lelestacia.gamediscountreminder.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.gamediscountreminder.domain.repository.StoreRepository
import com.lelestacia.gamediscountreminder.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val repository: StoreRepository
) : ViewModel() {

    private val _setupStore: MutableStateFlow<DataState<Boolean>> =
        MutableStateFlow(DataState.Loading)
    val setupStore: StateFlow<DataState<Boolean>> =
        _setupStore.asStateFlow()

    fun setupStore() = viewModelScope.launch {
        repository.setupStore().collectLatest { result ->
            _setupStore.update { result }
        }
    }
}