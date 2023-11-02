package com.muammarahlnn.urflix.core.datastore.di

import android.content.Context
import com.muammarahlnn.urflix.core.datastore.UrflixPreferencesDataStore
import com.muammarahlnn.urflix.core.datastore.urflixPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DataStoreModule, 02/11/2023 18.06 by Muammar Ahlan Abimanyu
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providesUrflixPreferencesDataStore(
        @ApplicationContext context: Context
    ): UrflixPreferencesDataStore = UrflixPreferencesDataStore(
        context.urflixPreferences
    )
}