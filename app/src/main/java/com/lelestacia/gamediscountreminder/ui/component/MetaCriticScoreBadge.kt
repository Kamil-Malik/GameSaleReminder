package com.lelestacia.gamediscountreminder.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.gamediscountreminder.ui.theme.GameSaleReminderTheme
import com.lelestacia.gamediscountreminder.ui.theme.GreenMetaCritic
import com.lelestacia.gamediscountreminder.ui.theme.RedMetaCritic
import com.lelestacia.gamediscountreminder.ui.theme.YellowMetaCritic
import com.lelestacia.gamediscountreminder.ui.theme.fontFamily

@Composable
fun MetaCriticScoreBadge(score: Int) {

    val backgroundColor =
        when {
            score > 70 -> GreenMetaCritic
            score > 40 -> YellowMetaCritic
            else -> RedMetaCritic
        }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        if (score != 0) {
            Text(
                text = score.toString(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    fontFamily = fontFamily
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewMetaCriticScoreBadge() {
    GameSaleReminderTheme {
        Surface {
            MetaCriticScoreBadge(score = 71)
        }
    }
}