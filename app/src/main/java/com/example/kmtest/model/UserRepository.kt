package com.example.kmtest.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kmtest.ui.UserPagingSource
import com.example.kmtest.service.api.ApiService
import com.example.kmtest.service.response.DataItem
import com.example.kmtest.service.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UserRepository private constructor(private val apiService: ApiService) {

    private val _listUser = MutableLiveData<List<DataItem>>()
    val listUser: LiveData<List<DataItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(config = PagingConfig(
            pageSize = 5
        ), pagingSourceFactory = {
            UserPagingSource(apiService)
        }).liveData
    }

    fun getAllUser() {
        _isLoading.value = true

        val client = apiService.getAllUser()

        client.enqueue(object: retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                try {
                    _isLoading.value = false
                    val user = response.body()?.data
                    if (response.isSuccessful && response.body() != null) {
                        _listUser.value = user!!
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    companion object {
        private const val TAG = "UserRepository"

        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            apiService: ApiService
        ): UserRepository = Companion.instance ?: synchronized(this) {
            instance ?: UserRepository(apiService)
        }
    }
}