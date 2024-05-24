package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.PersonResponse
import retrofit2.http.GET

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PersonApi, 24/05/2024 18.50
 */
interface PersonApi {

    @GET(GET_POPULAR_PEOPLE)
    suspend fun getPopularPeople(): BaseResults<PersonResponse>

    private companion object {

        const val GET_POPULAR_PEOPLE = "person/popular"
    }
}