package com.lelestacia.gamediscountreminder.data.source.remote.api

import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DealsEndpoint {

    @GET("deals")
    suspend fun getDeals(
        @Query(value = "pageNumber") pageNumber: Int,
        @Query(value = "storeID") storeID: Int? = 1,
        @Query(value = "onSale") boolean: Boolean = true
    ): List<GameDealDto>
}