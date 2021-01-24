package com.example.stockapp.ui.repository

import androidx.lifecycle.MutableLiveData
import com.example.stockapp.common.Constant
import com.example.stockapp.network.ApiInterface
import com.example.stockapp.network.RetrofitClient
import com.example.stockapp.ui.stock.model.StockParentItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


object StockDataRepository {

     suspend fun loadStocks(model: MutableLiveData<ArrayList<StockParentItem>>,type:String){
        var apiInterface: ApiInterface = RetrofitClient.getService(ApiInterface::class.java)
         var url=Constant.NIFTY_BANK
         if(type.contains("50")){
             url=Constant.NIFTY_50
         }
         if(type.contains("IT")){
             url=Constant.NIFTY_IT
         }
        val res=apiInterface.getStock(url)
        if(res!=null){
            model.postValue(res)
        }else{
            model.postValue(null)
        }
    }

}