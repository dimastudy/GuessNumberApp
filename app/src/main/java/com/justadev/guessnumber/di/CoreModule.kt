package com.justadev.guessnumber.di

import com.justadev.guessnumber.core.BaseResourceManager
import com.justadev.guessnumber.core.ResourceManager
import com.justadev.guessnumber.data.RandomNumbersRepository
import com.justadev.guessnumber.domain.BaseNumberValidator
import com.justadev.guessnumber.domain.NumberValidator
import com.justadev.guessnumber.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun bindResourceManager(resourceManager: BaseResourceManager): ResourceManager

    @Binds
    @Singleton
    fun bindRepository(repository: RandomNumbersRepository) : Repository

    @Binds
    @Singleton
    fun bindNumberValidator(numberValidator: BaseNumberValidator) : NumberValidator

}