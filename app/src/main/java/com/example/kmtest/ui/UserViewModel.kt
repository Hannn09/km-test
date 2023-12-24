package com.example.kmtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kmtest.model.UserRepository
import com.example.kmtest.service.response.DataItem
import com.example.kmtest.service.response.UserResponse
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    val getListUser: LiveData<PagingData<DataItem>> = userRepository.getUsers().cachedIn(viewModelScope)
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    val isLoading: LiveData<Boolean> = userRepository.isLoading
    val getAllUser: LiveData<List<DataItem>> = userRepository.listUser

    fun getAllUser() {
        viewModelScope.launch {
            userRepository.getAllUser()
        }
    }
}