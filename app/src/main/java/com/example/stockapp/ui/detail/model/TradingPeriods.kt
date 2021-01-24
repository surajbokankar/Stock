package com.example.stockapp.ui.detail.model

data class TradingPeriods(
    val post: List<List<PostX>>,
    val pre: List<List<PreX>>,
    val regular: List<List<RegularX>>
)