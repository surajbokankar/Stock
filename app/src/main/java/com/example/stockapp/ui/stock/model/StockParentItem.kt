package com.example.stockapp.ui.stock.model

data class StockParentItem(
    val change: Double,
    val dayHigh: Double,
    val dayLow: Double,
    val identifier: String,
    val lastPrice: Double,
    val lastUpdateTime: String,
    val `open`: Double,
    val pChange: Double,
    val previousClose: Double,
    val symbol: String,
    val totalTradedValue: Double,
    val totalTradedVolume: Int,
    val yearHigh: Double,
    val yearLow: Double
)