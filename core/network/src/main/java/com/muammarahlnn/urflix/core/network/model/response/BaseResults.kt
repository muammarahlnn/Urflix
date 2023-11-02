package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.Serializable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BaseResults, 02/11/2023 11.45 by Muammar Ahlan Abimanyu
 */
@Serializable
data class BaseResults<T>(
    val results: List<T>
)