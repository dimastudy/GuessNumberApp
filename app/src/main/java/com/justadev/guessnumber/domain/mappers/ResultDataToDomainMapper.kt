package com.justadev.guessnumber.domain.mappers

import com.justadev.guessnumber.data.entities.RandomNumberData
import com.justadev.guessnumber.domain.entities.ResultDomain

interface ResultDataToDomainMapper {

    fun map(number: RandomNumberData): ResultDomain

    fun map(e: Exception): ResultDomain

}