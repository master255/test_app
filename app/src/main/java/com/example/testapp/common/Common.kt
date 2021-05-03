package com.example.testapp.common

import com.example.testapp.`interface`.RetrofitServices
import com.example.testapp.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://official-joke-api.appspot.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}