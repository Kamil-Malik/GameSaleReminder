package com.lelestacia.gamediscountreminder.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lelestacia.gamediscountreminder.domain.model.Price
import com.lelestacia.gamediscountreminder.ui.theme.GameSaleReminderTheme
import com.lelestacia.gamediscountreminder.ui.theme.fontFamily
import com.lelestacia.gamediscountreminder.util.Constant

@Composable
fun StorePriceCard(
    logoUrl: String,
    storeName: String,
    price: Price
) {
    val imageUrl = Constant.BASE_URL + logoUrl
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = storeName, style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontFamily
                    )
                )
            }
            PriceCard(price = price)
        }
    }
}

@Preview
@Composable
fun PreviewStoreAndPriceCard() {
    GameSaleReminderTheme {
        Surface {
            StorePriceCard(
                logoUrl = "/img/stores/icons/21.png",
                storeName = "Gamesload",
                price = Price(
                    normal = "104.99",
                    sale = "15.99",
                    savings = "85"
                )
            )
        }
    }
}