package com.example.vehichleslist.network.fuel

import com.google.gson.annotations.SerializedName

data class FuelInfo(
    @SerializedName("name_fuel") val nameFuel: String
)