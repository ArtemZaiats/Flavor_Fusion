package com.flavorfusion.common_ui.di

import android.content.Context
import com.flavorfusion.common_ui.DefaultExecutor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.error.DefaultMessageExtractor
import com.flavorfusion.common_ui.error.ErrorMessageExtractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    fun providesErrorMessageExtractor(@ApplicationContext context: Context): ErrorMessageExtractor {
        return DefaultMessageExtractor(context)
    }

    @Provides
    fun providesExecutor(errorMessageExtractor: ErrorMessageExtractor): Executor {
        return DefaultExecutor(errorMessageExtractor)
    }
}