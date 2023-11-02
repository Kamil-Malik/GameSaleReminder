package com.lelestacia.gamediscountreminder.ui.screen.deal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenEvent
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenState
import com.lelestacia.gamediscountreminder.ui.component.GameDealCardItem
import com.lelestacia.gamediscountreminder.ui.theme.BaseBackgroundColor

/*
 * TODO: Adjust the background color and text color so it match, also font.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealScreen(
    deals: LazyPagingItems<DealDto>,
    sheetState: BottomSheetScaffoldState,
    screenState: DealScreenState,
    onEvent: (DealScreenEvent) -> Unit
) {
    BottomSheetScaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Deals")
                })
        },
        sheetContent = {
            screenState.selectedDeal?.let { selectedDeal ->
                GameDealBottomSheetContent(
                    onDismiss = onEvent,
                    deal = selectedDeal,
                    screenState = screenState
                )
            }
        },
        sheetSwipeEnabled = true,
        sheetPeekHeight = 0.dp,
        scaffoldState = sheetState
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseBackgroundColor)
                .padding(paddingValues)
        ) {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 8.dp
            ) {
                items(count = deals.itemCount) { index ->
                    val deal = deals[index]
                    if (deal != null) {
                        GameDealCardItem(
                            deal = deal,
                            onClicked = {
                                onEvent(DealScreenEvent.OnGameDealClicked(deal = it))
                            }
                        )
                    }
                }
            }
        }
    }
}