package com.justadev.guessnumber.di

import com.justadev.guessnumber.data.mappers.BaseRandomNumberToDomain
import com.justadev.guessnumber.data.mappers.BaseResultDataToDomain
import com.justadev.guessnumber.domain.mappers.RandomNumberDataToDomainMapper
import com.justadev.guessnumber.domain.mappers.ResultDataToDomainMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface MappersModule {

    @Binds
    @Singleton
    fun bindResultDataToDomainMapper(resultDataToDomain: BaseResultDataToDomain) : ResultDataToDomainMapper

    @Binds
    @Singleton
    fun bindNumberDataToDomainMapper(randomNumberDataToDomainMapper: BaseRandomNumberToDomain) : RandomNumberDataToDomainMapper


}