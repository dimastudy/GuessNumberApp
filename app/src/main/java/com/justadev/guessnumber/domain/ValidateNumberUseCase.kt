package com.justadev.guessnumber.domain

import javax.inject.Inject

class ValidateNumberUseCase @Inject constructor(
    private val numberValidator: NumberValidator
) {

    operator fun invoke(number: String?) : Boolean = numberValidator.isValid(number)

}