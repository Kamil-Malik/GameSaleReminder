package com.lelestacia.gamediscountreminder.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.gamediscountreminder.data.source.remote.api.DealsEndpoint
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto

class GameDealPaging(
    private val endpoint: DealsEndpoint
) : PagingSource<Int, DealDto>() {

    override fun getRefreshKey(state: PagingState<Int, DealDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DealDto> {
        return try {
            val pageNumber: Int = params.key ?: 1
            val result: List<DealDto> = endpoint.getDeals(pageNumber)

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
                } else {
                    pageNumber.plus(1)
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}