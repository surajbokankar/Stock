package com.example.stockapp.ui.stock

import androidx.lifecycle.*
import com.example.stockapp.ui.repository.StockDataRepository
import com.example.stockapp.ui.stock.model.StockParent
import com.example.stockapp.ui.stock.model.StockParentItem
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {

    private val mStocks=MutableLiveData<ArrayList<StockParentItem>>()
    fun loadStocks(stock:String)=viewModelScope.launch{
       StockDataRepository.loadStocks(mStocks,stock)
    }
    val stocksData by lazy {
        mStocks
    }
}