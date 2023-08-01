package com.justadev.guessnumber.data.mappers

import com.justadev.guessnumber.R
import com.justadev.guessnumber.core.ResourceManager
import com.justadev.guessnumber.data.entities.RandomNumberData
import com.justadev.guessnumber.domain.entities.ResultDomain
import com.justadev.guessnumber.domain.mappers.RandomNumberDataToDomainMapper
import com.justadev.guessnumber.domain.mappers.ResultDataToDomainMapper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BaseResultDataToDomain @Inject constructor(
    private val mapper: RandomNumberDataToDomainMapper,
    private val resourceManager: ResourceManager
) : ResultDataToDomainMapper {
    override fun map(number: RandomNumberData): ResultDomain {
        return ResultDomain.Success(number.map(mapper))
    }

    override fun map(e: Exception): ResultDomain {
        return when(e){
            is HttpException -> {
                ResultDomain.Error(resourceManager.getString(R.string.http_message))
            }
            is IOException -> {
                ResultDomain.Error(resourceManager.getString(R.string.io_message))
            }
            else -> {
                ResultDomain.Error(resourceManager.getString(R.string.default_error_message))
            }
        }
    }
}
