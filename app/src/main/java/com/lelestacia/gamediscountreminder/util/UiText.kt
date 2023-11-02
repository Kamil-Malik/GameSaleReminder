package com.lelestacia.gamediscountreminder.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class UiResource(@StringRes val res: Int) : UiText()
    data class UiMessage(val message: String) : UiText()


    /*
     * TODO: Fix function getString so can be called whether from composable or not
     */

    @Composable
    fun getString(): String {
        return when (this) {
            is UiMessage -> message

            is UiResource -> stringResource(id = res)
        }
    }
}