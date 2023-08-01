package com.justadev.guessnumber.domain.entities

sealed class ResultDomain {

    data class Success(val data: RandomNumber) : ResultDomain()
    data class Error(val message: String) : ResultDomain()
    object Loading : ResultDomain()

}