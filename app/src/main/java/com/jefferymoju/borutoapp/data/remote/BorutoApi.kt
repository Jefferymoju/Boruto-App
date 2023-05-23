package com.jefferymoju.borutoapp.data.remote

import com.jefferymoju.borutoapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("/boruto/heroes")   // to get the heroes from our endpoint with retrofit
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1  // Query parameter for paginating through our server response
    ): ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse
}