package com.lelestacia.gamediscountreminder.util

sealed class Destination(val route: String) {
    data object LoadingScreen : Destination("loading")
    data object GameDealScreen : Destination("deals")
}