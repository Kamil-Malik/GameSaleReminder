package com.lelestacia.gamediscountreminder.data.source.remote.dto.game


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDetailDto(

    @Json(name = "cheapestPriceEver")
    val cheapestPriceEver: GameDetailCheapestPriceEverDto,

    @Json(name = "deals")
    val deals: List<GameDetailDealDto>,

    @Json(name = "info")
    val info: GameDetailInfoDto
)