package com.example.poetryapp.Api

import com.example.poetryapp.response.GetPoetryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("getpoetry.php")
    fun getPoetry(): Call<GetPoetryResponse>

}