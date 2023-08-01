package com.justadev.guessnumber.data.network

import com.justadev.guessnumber.data.entities.ResultData

interface CloudDataSource {

    suspend fun getRandomNumber(min: Int, max: Int) : ResultData

}