package com.lelestacia.gamediscountreminder.util

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failed(val error: UiText) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
    data object None : DataState<Nothing>()
}