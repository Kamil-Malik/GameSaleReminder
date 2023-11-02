package com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DealDetailDto(
    @Json(name = "cheaperStores")
    val cheaperStores: List<DealDetailCheaperStoreDto>,
    @Json(name = "cheapestPrice")
    val cheapestPrice: DealDetailCheapestPriceDto,
    @Json(name = "gameInfo")
    val gameInfo: DealDetailGameInfoDto
)