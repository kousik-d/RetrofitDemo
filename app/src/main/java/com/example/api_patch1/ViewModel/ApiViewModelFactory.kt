package com.example.api_patch1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api_patch1.repository.ApiRepository

class ApiViewModelFactory(private val repository: ApiRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  ApiViewModel(repository) as T
    }
}