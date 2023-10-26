package com.lelestacia.gamediscountreminder.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.gamediscountreminder.domain.model.Price
import com.lelestacia.gamediscountreminder.ui.theme.GameDiscountReminderTheme
import com.lelestacia.gamediscountreminder.ui.theme.GreenSaleBackground
import com.lelestacia.gamediscountreminder.ui.theme.GreenSaleText
import com.lelestacia.gamediscountreminder.ui.theme.PriceBackground
import com.lelestacia.gamediscountreminder.ui.theme.fontFamily
import kotlin.math.roundToInt

@Composable
fun PriceCard(price: Price) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(GreenSaleBackground)
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = "-${price.savings.toDouble().roundToInt()}%",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontFamily = fontFamily,
                        color = GreenSaleText
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .background(PriceBackground)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = "${price.normal}$",
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.LightGray
                    )
                )
                Text(
                    text = "${price.sale} $",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = GreenSaleText
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPriceCard() {
    GameDiscountReminderTheme {
        Surface {
            PriceCard(
                price = Price(
                    normal = "9.99",
                    sale = "1.99",
                    savings = "80.080080"
                )
            )
        }
    }
}