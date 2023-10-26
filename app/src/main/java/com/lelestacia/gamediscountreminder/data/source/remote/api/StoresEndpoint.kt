package com.lelestacia.gamediscountreminder.data.source.remote.api

import com.lelestacia.gamediscountreminder.data.source.remote.dto.store.StoreDto
import retrofit2.http.GET

interface StoresEndpoint {

    @GET("stores")
    suspend fun getStores(): List<StoreDto>
}