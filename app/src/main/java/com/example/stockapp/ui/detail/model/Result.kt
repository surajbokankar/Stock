package com.example.stockapp.ui.detail.model

data class Result(
    val indicators: Indicators,
    val meta: Meta,
    val timestamp: List<Int>
)