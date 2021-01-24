package com.example.stockapp.network

import com.example.stockapp.App
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var BASE_URL: String? = null
    private val cacheSize = 10 * 1024 * 1024 // 10 MB
    /**
     * init retrofit instance
     */
    fun init(baseURL: String) {
        BASE_URL = baseURL
        getInstance()
    }

    /**
     * get retrofit instance
     */
    fun getInstance(): Retrofit {
        val cache = Cache(App.instance.getCacheDir(), cacheSize.toLong())
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(onlineInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL!!)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        return retrofit!!
    }


    /**
     * init api services
     */
    fun <T> getService(service: Class<T>): T {
        return getInstance().create(service)
    }

    /**
     * online interceptor
     */
    internal var onlineInterceptor: Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge =
            10 * 10 // read from cache for 300(60 min.) seconds even if there is internet connection
        response.newBuilder().
            addHeader("X-RapidAPI-Key", "9fc376c7d4msh6cb146c6772c6dap172a68jsn8407560eed55")
            .removeHeader("Pragma")
            .build()
    }


}