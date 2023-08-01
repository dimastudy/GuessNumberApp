package com.justadev.guessnumber.domain

interface NumberValidator {

    fun isValid(text: String?) : Boolean

}