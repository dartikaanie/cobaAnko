package com.example.sub1.rest

import com.example.sub1.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sub1.BuildConfig.BASE_URL




object RetrofitInstances {
    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}