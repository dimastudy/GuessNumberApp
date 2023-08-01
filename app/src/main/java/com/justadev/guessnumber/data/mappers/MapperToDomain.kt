package com.justadev.guessnumber.data.mappers

import com.justadev.guessnumber.domain.entities.ResultDomain
import com.justadev.guessnumber.domain.mappers.ResultDataToDomainMapper

interface MapperToDomain {

    fun map(mapper: ResultDataToDomainMapper) : ResultDomain

}