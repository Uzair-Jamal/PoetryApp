package com.example.poetryapp.response

import com.example.poetryapp.PoetryModel
import com.google.gson.annotations.SerializedName

data class GetPoetryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: List<PoetryModel>
)