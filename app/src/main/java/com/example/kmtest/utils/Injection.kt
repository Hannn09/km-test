package com.example.kmtest.utils

import android.content.Context
import com.example.kmtest.model.UserRepository
import com.example.kmtest.service.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}