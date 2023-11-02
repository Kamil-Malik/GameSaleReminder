package com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DealDetailCheaperStoreDto(
    @Json(name = "dealID")
    val dealID: String,
    @Json(name = "retailPrice")
    val retailPrice: String,
    @Json(name = "salePrice")
    val salePrice: String,
    @Json(name = "storeID")
    val storeID: String
)