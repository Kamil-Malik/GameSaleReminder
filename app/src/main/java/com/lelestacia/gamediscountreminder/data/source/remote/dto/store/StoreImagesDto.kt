package com.lelestacia.gamediscountreminder.data.source.remote.dto.store


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreImagesDto(

    @Json(name = "banner")
    val banner: String,

    @Json(name = "icon")
    val icon: String,

    @Json(name = "logo")
    val logo: String
)