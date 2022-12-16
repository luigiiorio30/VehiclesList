package com.example.vehichleslist.network.types

import com.google.gson.annotations.SerializedName

data class TypeInfo(
    @SerializedName("car_type") val nameType: String
)