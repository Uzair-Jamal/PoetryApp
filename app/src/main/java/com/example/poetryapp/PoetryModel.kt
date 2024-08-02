package com.example.poetryapp

import com.google.gson.annotations.SerializedName

data class PoetryModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poetryData")
    val poetryData: String,
    @SerializedName("poetName")
    val poetName: String,
    @SerializedName("dateTime")
    val dateTime: String
)
