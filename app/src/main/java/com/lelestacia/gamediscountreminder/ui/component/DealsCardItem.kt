package com.lelestacia.gamediscountreminder.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.domain.model.Price
import com.lelestacia.gamediscountreminder.ui.theme.CardBackgroundColor
import com.lelestacia.gamediscountreminder.ui.theme.GameSaleReminderTheme
import com.lelestacia.gamediscountreminder.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealCardItem(
    deal: DealDto,
    onClicked: (DealDto) -> Unit
) {

    val onCardClicked: () -> Unit = {
        onClicked(deal)
    }

    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackgroundColor
        ),
        onClick = onCardClicked,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = deal.thumb,
                contentScale = ContentScale.FillWidth,
                contentDescription = deal.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = deal.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PriceCard(
                        price = Price(
                            normal = deal.normalPrice,
                            sale = deal.salePrice,
                            savings = deal.savings
                        )
                    )
                    MetaCriticScoreBadge(score = deal.metacriticScore.toInt())
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDealsCardItem() {
    GameSaleReminderTheme {
        Surface {
            GameDealCardItem(
                deal = DealDto(
                    dealID = "CoyryML0LMR039mrHIRiquMVW%2FpRm6zbecdtqhyL1L0%3D",
                    dealRating = "9.4",
                    gameID = "109696",
                    internalName = "DISTANTWORLDSUNIVERSE",
                    isOnSale = "1",
                    lastChange = 1698091241,
                    metacriticLink = "/game/distant-worlds-universe/",
                    metacriticScore = "81",
                    normalPrice = "29.99",
                    releaseDate = 1400803200,
                    salePrice = "2.99",
                    savings = "90.030010",
                    steamAppID = "261470",
                    steamRatingCount = "1345",
                    steamRatingPercent = "72",
                    steamRatingText = "Mostly Positive",
                    storeID = "1",
                    thumb = "https://cdn.cloudflare.steamstatic.com/steam/apps/261470/capsule_sm_120.jpg?t=1564046653",
                    title = "Distant Worlds: Universe"
                ),
                onClicked = {}
            )
        }
    }
}


