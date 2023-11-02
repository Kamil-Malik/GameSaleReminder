package com.lelestacia.gamediscountreminder.ui.screen.deal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.domain.model.Price
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenEvent
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenState
import com.lelestacia.gamediscountreminder.ui.component.PriceCard
import com.lelestacia.gamediscountreminder.ui.theme.BaseBackgroundColor
import com.lelestacia.gamediscountreminder.ui.theme.fontFamily
import com.lelestacia.gamediscountreminder.ui.util.getTextColorForRating
import com.lelestacia.gamediscountreminder.util.Constant
import com.lelestacia.gamediscountreminder.util.DataState
import com.lelestacia.gamediscountreminder.util.calculateSavings
import com.lelestacia.gamediscountreminder.util.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealBottomSheetContent(
    onDismiss: (DealScreenEvent) -> Unit,
    deal: DealDto,
    screenState: DealScreenState
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss(DealScreenEvent.OnBottomSheetDiscarded) },
        containerColor = BaseBackgroundColor

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = deal.thumb,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .clip(RoundedCornerShape(4.dp))
            )

            Text(
                text = deal.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontFamily = fontFamily,
                    color = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Release Date :",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily
                        )
                    )
                    Text(
                        text = "Review :",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily
                        )
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = deal.releaseDate.toLong().toFormattedDate(),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )

                    Row {
                        Text(
                            text = "${deal.steamRatingText} (",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = getTextColorForRating(deal.steamRatingText.orEmpty())
                            )
                        )
                        Text(
                            text = deal.steamRatingCount.orEmpty(),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                        Text(
                            text = ")",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = getTextColorForRating(deal.steamRatingText.orEmpty())
                            )
                        )
                    }
                }

            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 64.dp)
            ) {
                when (screenState.selectedDealDetail) {
                    is DataState.Failed -> Unit
                    DataState.Loading -> CircularProgressIndicator()
                    DataState.None -> Unit
                    is DataState.Success -> {
                        LazyRow {
                            items(count = screenState.selectedDealDetail.data.cheaperStores.size) { key ->

                                val selectedStore =
                                    screenState.selectedDealDetail.data.cheaperStores[key]

                                val store =
                                    screenState.store.find { it.storeID == selectedStore.storeID }

                                Card(
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        AsyncImage(
                                            model = Constant.BASE_URL + store?.logo,
                                            contentDescription = null
                                        )

                                        PriceCard(
                                            price = Price(
                                                normal = selectedStore.retailPrice,
                                                sale = selectedStore.salePrice,
                                                savings = calculateSavings(
                                                    normalPrice = selectedStore.retailPrice.toDouble(),
                                                    salePrice = selectedStore.salePrice.toDouble()
                                                ).toString()
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}