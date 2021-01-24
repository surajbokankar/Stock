package com.example.stockapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapp.ui.detail.model.StockDetailParent
import com.example.stockapp.ui.repository.StockDataRepository
import com.example.stockapp.ui.stock.model.StockParentItem
import kotlinx.coroutines.launch

class StockDetailViewModel:ViewModel() {
    private val mStocks= MutableLiveData<StockDetailParent>()
    fun loadStocks(symbol:String,range:String)=viewModelScope.launch{
        StockDetailRepository.loadStockDetail(symbol,range,mStocks)
    }
    val stocksData by lazy {
        mStocks
    }
}