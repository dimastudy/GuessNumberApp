package com.justadev.guessnumber.data.network

import retrofit2.http.GET
import retrofit2.http.Query


interface RandomNumberApi {

    @GET("randomredditnumber")
    suspend fun getRandomNumber(
        @Query("min") min: Int,
        @Query("max") max: Int,
    ) : List<Int>

}