package com.muammarahlnn.urflix.core.data.mapper

import com.muammarahlnn.urflix.core.datastore.model.UserEntity
import com.muammarahlnn.urflix.core.model.data.UserModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileMapper, 02/11/2023 18.12 by Muammar Ahlan Abimanyu
 */

fun UserEntity.toUserModel() = UserModel(
    fullName = fullName,
    email = email,
)