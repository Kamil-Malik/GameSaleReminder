package com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DealDetailCheapestPriceDto(
    @Json(name = "date")
    val date: Int,
    @Json(name = "price")
    val price: String
)