package com.lelestacia.gamediscountreminder.data.source.remote.dto.game


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDetailCheapestPriceEverDto(
    @Json(name = "date")
    val date: Int,
    @Json(name = "price")
    val price: String
)