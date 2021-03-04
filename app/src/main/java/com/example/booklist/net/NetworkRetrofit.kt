package com.example.booklist.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRetrofit {
    var retrofit: Retrofit? = null
    val BASE_URL = "https://www.googleapis.com"

    fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}