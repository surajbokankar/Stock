package com.example.stockapp

import android.app.Application
import android.content.Context
import com.example.stockapp.common.SharedPrefData
import com.example.stockapp.network.RetrofitClient

class App :Application() {
    companion object{
        lateinit var instance: App
        lateinit var mContext: Context

        fun setContext(context: Context) {
            mContext = context
        }

        fun getActivityContext(): Context {
            return mContext
        }

    }
    override fun onCreate() {
        super.onCreate()
        instance=this
        SharedPrefData.setInt(SharedPrefData.SORT_VALUE,-1)
        RetrofitClient.init("https://apidojo-yahoo-finance-v1.p.rapidapi.com/")
    }
}