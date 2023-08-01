package com.justadev.guessnumber.data.mappers

import com.justadev.guessnumber.domain.entities.RandomNumber
import com.justadev.guessnumber.domain.mappers.RandomNumberDataToDomainMapper
import javax.inject.Inject

class BaseRandomNumberToDomain @Inject constructor() : RandomNumberDataToDomainMapper {
    override fun map(number: Int): RandomNumber =
        RandomNumber(number)
}