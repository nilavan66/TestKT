package com.example.testkt.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInt private constructor() {
    val retrofit: ApiService = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    companion object {
        @Volatile
        private var instance: RetrofitInt? = null

        fun getInstance(): RetrofitInt {
            return instance ?: synchronized(this) {
                instance ?: RetrofitInt().also { instance = it }
            }
        }
    }

}
