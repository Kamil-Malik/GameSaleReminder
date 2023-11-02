package com.lelestacia.gamediscountreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.gamediscountreminder.ui.theme.GameSaleReminderTheme
import com.lelestacia.gamediscountreminder.ui.theme.fontFamily

@Composable
fun CustomLoadingScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        CircularProgressIndicator()

        Text(
            text = "Preparing...",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontFamily
            )
        )
    }
}

@Preview
@Composable
fun PreviewCustomLoadingScreen() {
    GameSaleReminderTheme {
        Surface {
            CustomLoadingScreen()
        }
    }
}