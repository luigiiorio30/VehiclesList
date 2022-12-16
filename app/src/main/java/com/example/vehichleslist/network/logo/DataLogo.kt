package com.example.vehichleslist.network

import com.google.gson.annotations.SerializedName

data class Logo(
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
)
