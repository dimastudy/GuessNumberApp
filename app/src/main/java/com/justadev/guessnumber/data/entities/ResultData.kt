package com.justadev.guessnumber.data.entities

import com.justadev.guessnumber.data.mappers.MapperToDomain
import com.justadev.guessnumber.domain.entities.ResultDomain
import com.justadev.guessnumber.domain.mappers.RandomNumberDataToDomainMapper
import com.justadev.guessnumber.domain.mappers.ResultDataToDomainMapper

sealed class ResultData : MapperToDomain {

    data class Success(val data: RandomNumberData) : ResultData() {
        override fun map(mapper: ResultDataToDomainMapper): ResultDomain {
            return mapper.map(data)
        }

    }

    data class Error(val exception: Exception) : ResultData() {
        override fun map(mapper: ResultDataToDomainMapper): ResultDomain {
            return mapper.map(exception)
        }

    }

}
