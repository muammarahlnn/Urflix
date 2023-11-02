package com.muammarahlnn.urflix.core.data.repository.impl

import com.muammarahlnn.urflix.core.data.mapper.toUserModel
import com.muammarahlnn.urflix.core.data.repository.ProfileRepository
import com.muammarahlnn.urflix.core.datastore.UrflixPreferencesDataStore
import com.muammarahlnn.urflix.core.datastore.model.UserEntity
import com.muammarahlnn.urflix.core.model.data.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileRepositoryImpl, 02/11/2023 18.10 by Muammar Ahlan Abimanyu
 */
class ProfileRepositoryImpl @Inject constructor(
    private val urflixPreferences: UrflixPreferencesDataStore
) : ProfileRepository {

    override suspend fun saveUser(fullName: String, email: String) =
        urflixPreferences.saveUser(
            UserEntity(
                fullName = fullName,
                email = email,
            )
        )

    override fun getUser(user: UserModel): Flow<UserModel> =
        urflixPreferences.getUser().map {
            it.toUserModel()
        }
}