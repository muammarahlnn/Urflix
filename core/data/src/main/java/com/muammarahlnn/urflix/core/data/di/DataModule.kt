package com.muammarahlnn.urflix.core.data.di

import com.muammarahlnn.urflix.core.data.repository.BookmarksRepository
import com.muammarahlnn.urflix.core.data.repository.FilmDetailsRepository
import com.muammarahlnn.urflix.core.data.repository.HomeRepository
import com.muammarahlnn.urflix.core.data.repository.ProfileRepository
import com.muammarahlnn.urflix.core.data.repository.impl.BookmarksRepositoryImpl
import com.muammarahlnn.urflix.core.data.repository.impl.FilmDetailsRepositoryImpl
import com.muammarahlnn.urflix.core.data.repository.impl.HomeRepositoryImpl
import com.muammarahlnn.urflix.core.data.repository.impl.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DataModule, 02/11/2023 12.44 by Muammar Ahlan Abimanyu
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsHomeRepository(
        homeRepository: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    fun bindsProfileRepository(
        profileRepository: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    fun bindsFilmDetailsRepository(
        filmDetailsRepository: FilmDetailsRepositoryImpl
    ): FilmDetailsRepository

    @Binds
    fun bindsBookmakrsRepository(
        bookmarksRepository: BookmarksRepositoryImpl
    ): BookmarksRepository
}