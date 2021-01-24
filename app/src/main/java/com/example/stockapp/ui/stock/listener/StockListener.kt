package com.example.stockapp.ui.stock.listener

import com.example.stockapp.ui.stock.model.StockParentItem

interface StockListener {
    fun onStockClick(item:StockParentItem)
    fun onSortClick(type:Int)
}