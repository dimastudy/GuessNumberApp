package com.justadev.guessnumber.domain

import com.justadev.guessnumber.domain.entities.ResultDomain

interface Repository {

    suspend fun getRandomNumber(min: Int, max: Int) : ResultDomain

}