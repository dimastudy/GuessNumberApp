package com.justadev.guessnumber.core

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes id: Int) : String

}