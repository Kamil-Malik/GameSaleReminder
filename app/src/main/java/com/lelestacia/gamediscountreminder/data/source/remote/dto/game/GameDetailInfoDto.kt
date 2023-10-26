package com.lelestacia.gamediscountreminder.data.source.remote.dto.game


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDetailInfoDto(
    @Json(name = "steamAppID")
    val steamAppID: String,
    @Json(name = "thumb")
    val thumb: String,
    @Json(name = "title")
    val title: String
)