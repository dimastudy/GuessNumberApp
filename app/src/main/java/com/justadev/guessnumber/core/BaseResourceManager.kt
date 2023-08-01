package com.justadev.guessnumber.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BaseResourceManager @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager{
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}