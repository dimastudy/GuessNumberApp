package com.justadev.guessnumber.domain

import javax.inject.Inject

class BaseNumberValidator @Inject constructor() : NumberValidator {
    override fun isValid(text: String?): Boolean {
        return try {
            val number = text?.toInt()
            number in 0..100
        } catch (e: Exception) {
            false
        }
    }
}