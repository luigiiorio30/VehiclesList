package com.example.vehichleslist.network.types

import com.example.vehichleslist.network.fuel.FuelInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL =
    "https://raw.githubusercontent.com/luigiiorio30/Car-type/main/"

var gsonType: Gson = GsonBuilder()
    .setLenient()
    .create()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonType))
        .build()

interface CarTypeApiService {
    /**
     * Returns a [List] of [FuelInfo] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "year" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("Content-Type: application/json")
    @GET("car_types.json")
    suspend fun getCarTypeInfo(): List<TypeInfo>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CarTypeApi {
    val retrofitService: CarTypeApiService by lazy { retrofit.create(CarTypeApiService::class.java) }
}