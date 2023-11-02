package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.datastore.model.UserEntity
import com.muammarahlnn.urflix.core.model.data.UserModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileRepository, 02/11/2023 18.08 by Muammar Ahlan Abimanyu
 */
interface ProfileRepository {

    suspend fun saveUser(
        fullName: String,
        email: String,
    )

    fun getUser(user: UserModel): Flow<UserModel>
}