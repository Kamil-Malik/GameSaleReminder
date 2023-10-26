package com.lelestacia.gamediscountreminder.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.GameDealDto

class GameDealPaging(
    private val endpoint: DealsEndpoint
) : PagingSource<Int, GameDealDto>() {

    override fun getRefreshKey(state: PagingState<Int, GameDealDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDealDto> {
        return try {
            val pageNumber = params.key ?: 1
            val result = endpoint.getDeals(pageNumber)
            LoadResult.Page(
                data = result,
                prevKey =
                if (pageNumber == 1) {
                    null
                } else {
                    pageNumber.minus(1)
                },
                nextKey = if (result.isEmpty()) {
                    null
                }else {
                    pageNumber.plus(1)
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}