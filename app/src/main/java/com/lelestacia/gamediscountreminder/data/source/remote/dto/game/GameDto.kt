package com.lelestacia.gamediscountreminder.data.source.remote.dto.game


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDto(

    @Json(name = "cheapest")
    val cheapest: String,

    @Json(name = "cheapestDealID")
    val cheapestDealID: String,

    @Json(name = "external")
    val `external`: String,

    @Json(name = "gameID")
    val gameID: String,

    @Json(name = "internalName")
    val internalName: String,

    @Json(name = "steamAppID")
    val steamAppID: String,

    @Json(name = "thumb")
    val thumb: String
)