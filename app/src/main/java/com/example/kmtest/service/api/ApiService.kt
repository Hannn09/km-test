package com.example.kmtest.service.api

import com.example.kmtest.service.response.DataItem
import com.example.kmtest.service.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Response<UserResponse>

    @GET("users")
    fun getAllUser() : Call<UserResponse>
}