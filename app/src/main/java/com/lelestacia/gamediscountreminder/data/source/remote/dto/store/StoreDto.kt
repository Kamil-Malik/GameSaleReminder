package com.lelestacia.gamediscountreminder.data.source.remote.dto.store


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreDto(

    @Json(name = "images")
    val images: StoreImagesDto,

    @Json(name = "isActive")
    val isActive: Int,

    @Json(name = "storeID")
    val storeID: String,

    @Json(name = "storeName")
    val storeName: String
)