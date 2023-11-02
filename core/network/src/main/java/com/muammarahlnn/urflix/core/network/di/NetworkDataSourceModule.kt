package com.muammarahlnn.urflix.core.network.di

import com.muammarahlnn.urflix.core.network.datasource.HomeNetworkDataSource
import com.muammarahlnn.urflix.core.network.datasource.impl.HomeNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file NetworkDataSourceModule, 02/11/2023 12.17 by Muammar Ahlan Abimanyu
 */
@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsHomeNetworkDataSource(
        homeNetworkDataSource: HomeNetworkDataSourceImpl
    ): HomeNetworkDataSource
}