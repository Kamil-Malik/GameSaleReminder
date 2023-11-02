package com.lelestacia.gamediscountreminder.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val date = Date(this * 1000)
    val sdf = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.ENGLISH)
    return sdf.format(date)
}