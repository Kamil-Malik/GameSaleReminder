package com.lelestacia.gamediscountreminder.ui.util

import androidx.compose.ui.graphics.Color

fun getTextColorForRating(rating: String): Color {
    return when {
        rating.contains("Positive") -> Color.Blue.copy(
            alpha = 0.5F
        )

        rating.contains("Negative") -> Color.Red.copy(
            alpha = 0.5F
        )

        else -> Color.Yellow.copy(
            alpha = 0.5F
        )
    }
}