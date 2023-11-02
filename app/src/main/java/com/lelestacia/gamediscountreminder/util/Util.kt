package com.lelestacia.gamediscountreminder.util

import kotlin.math.roundToInt

fun calculateSavings(normalPrice: Double, salePrice: Double): Int {
    return (((normalPrice - salePrice) / normalPrice) * 100).roundToInt()
}