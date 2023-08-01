package com.justadev.guessnumber.domain.mappers

import com.justadev.guessnumber.domain.entities.RandomNumber

interface RandomNumberDataToDomainMapper {

    fun map(
        number: Int
    ) : RandomNumber

}