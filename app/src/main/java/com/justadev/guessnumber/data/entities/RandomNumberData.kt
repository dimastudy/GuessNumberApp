package com.justadev.guessnumber.data.entities

import com.justadev.guessnumber.domain.entities.RandomNumber
import com.justadev.guessnumber.domain.mappers.RandomNumberDataToDomainMapper

data class RandomNumberData(
    val number: Int
) {
    fun map(mapper: RandomNumberDataToDomainMapper) : RandomNumber = mapper.map(number)
}