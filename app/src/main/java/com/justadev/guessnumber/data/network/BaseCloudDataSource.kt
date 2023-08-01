package com.justadev.guessnumber.data.network

import com.justadev.guessnumber.data.entities.RandomNumberData
import com.justadev.guessnumber.data.entities.ResultData
import javax.inject.Inject

class BaseCloudDataSource @Inject constructor(
    private val api: RandomNumberApi
) : CloudDataSource {
    override suspend fun getRandomNumber(min: Int, max: Int): ResultData {
        return try {
            val number = api.getRandomNumber(min, max).first()
            ResultData.Success(RandomNumberData(number))
        } catch (e: Exception) {
            ResultData.Error(e)
        }
    }
}