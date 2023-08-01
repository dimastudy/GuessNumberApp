package com.justadev.guessnumber.data

import com.justadev.guessnumber.data.network.CloudDataSource
import com.justadev.guessnumber.domain.Repository
import com.justadev.guessnumber.domain.entities.ResultDomain
import com.justadev.guessnumber.domain.mappers.ResultDataToDomainMapper
import javax.inject.Inject

class RandomNumbersRepository @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val resultDataToDomainMapper: ResultDataToDomainMapper
) : Repository {
    override suspend fun getRandomNumber(min: Int, max: Int): ResultDomain {
        return cloudDataSource.getRandomNumber(min, max).map(resultDataToDomainMapper)
    }
}