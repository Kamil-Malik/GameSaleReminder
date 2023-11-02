package com.lelestacia.gamediscountreminder.data.source.remote.api

import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal_detail.DealDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DealsEndpoint {

    @GET("deals")
    suspend fun getDeals(
        @Query(value = "pageNumber") pageNumber: Int,
        @Query(value = "storeID") storeID: Int? = 1,
        @Query(value = "onSale") boolean: Boolean = true
    ): List<DealDto>

    @GET("deals")
    suspend fun getDealByID(
        @Query(value = "id") id: String
    ): DealDetailDto
}