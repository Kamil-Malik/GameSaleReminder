package com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DealDetailGameInfoDto(

    @Json(name = "gameID")
    val gameID: String,

    @Json(name = "metacriticLink")
    val metacriticLink: String?,

    @Json(name = "metacriticScore")
    val metacriticScore: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "publisher")
    val publisher: String,

    @Json(name = "releaseDate")
    val releaseDate: Int,

    @Json(name = "retailPrice")
    val retailPrice: String,

    @Json(name = "salePrice")
    val salePrice: String,

    @Json(name = "steamAppID")
    val steamAppID: String?,

    @Json(name = "steamRatingCount")
    val steamRatingCount: String,

    @Json(name = "steamRatingPercent")
    val steamRatingPercent: String,

    @Json(name = "steamRatingText")
    val steamRatingText: String?,

    @Json(name = "steamworks")
    val steamworks: String,

    @Json(name = "storeID")
    val storeID: String,

    @Json(name = "thumb")
    val thumb: String
)
