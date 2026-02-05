package com.zaiatsdevelopment.common_data.di

import com.zaiatsdevelopment.common_data.remote.model.DefaultResponseHandler
import com.zaiatsdevelopment.common_data.remote.model.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    fun providesResponseHandler(): ResponseHandler = DefaultResponseHandler()
}