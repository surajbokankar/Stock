package com.example.stockapp.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.stockapp.common.Constant.INTERVAL
import com.example.stockapp.common.Constant.RAPID_HOST
import com.example.stockapp.common.Constant.REGION
import com.example.stockapp.common.Constant.STOCK_CHART
import com.example.stockapp.network.ApiInterface
import com.example.stockapp.network.RetrofitClient
import com.example.stockapp.ui.detail.model.StockDetailParent

object StockDetailRepository {

    suspend fun loadStockDetail(symbol:String,time:String,model:MutableLiveData<StockDetailParent>){
        var apiInterface: ApiInterface = RetrofitClient.getService(ApiInterface::class.java)
        val res=apiInterface.getChart(STOCK_CHART)
        if(res!=null){
            model.postValue(res)
        }else{
            model.postValue(null)
        }
    }

    suspend fun loadStocks(symbol:String,time:String,model:MutableLiveData<StockDetailParent>){
        var apiInterface: ApiInterface = RetrofitClient.getService(ApiInterface::class.java)
        val res=apiInterface.getStockChart(RAPID_HOST,INTERVAL,symbol,time,REGION)
        if(res!=null){
            model.postValue(res)
        }else{
            model.postValue(null)
        }
    }

}