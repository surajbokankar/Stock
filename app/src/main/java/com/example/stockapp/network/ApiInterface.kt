package com.example.stockapp.network

import com.example.stockapp.ui.detail.model.StockDetailParent
import com.example.stockapp.ui.stock.model.StockParentItem
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {
    @GET("prices")
    suspend fun getIndices(@Query("Indices")  indices:String): ArrayList<StockParentItem>

    @GET
    suspend fun getStock(@Url url:String): ArrayList<StockParentItem>

    @GET("stock/v2/get-chart")
    suspend fun getStockChart(@Header("X-RapidAPI-Host") host:String,
                              @Query("interval") interval:String,
                              @Query("symbol") symbol:String,
                              @Query("range") range:String,
                              @Query("region") region:String
                              ): StockDetailParent

    @GET
    suspend fun getChart(@Url url:String):StockDetailParent
}