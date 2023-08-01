package com.justadev.guessnumber.domain

import com.justadev.guessnumber.domain.entities.ResultDomain
import javax.inject.Inject

class GetRandomNumberUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke() : ResultDomain {
        return repository.getRandomNumber(1,100)
    }

}