package com.lelestacia.gamediscountreminder.data.source.remote.dto.game


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDetailDealDto(

    @Json(name = "dealID")
    val dealID: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "retailPrice")
    val retailPrice: String,

    @Json(name = "savings")
    val savings: String,

    @Json(name = "storeID")
    val storeID: String
)