package com.example.testapp.`interface`

import com.example.testapp.models.Joke
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("random_ten")
    fun getJokesList(): Call<MutableList<Joke>>
}