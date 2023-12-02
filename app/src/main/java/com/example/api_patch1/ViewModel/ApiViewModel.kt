package com.example.api_patch1.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_patch1.Posts
import com.example.api_patch1.repository.ApiRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ApiViewModel(private val apiRepository: ApiRepository) :ViewModel(){


    val allPosts : MutableLiveData<List<Posts>> = MutableLiveData()

    val responseForId : MutableLiveData<Posts> = MutableLiveData()

    fun insertPost(posts: Posts) = viewModelScope.async(IO){
        apiRepository.insertPost(posts)
    }

    fun getAllPosts() = viewModelScope.launch {
        val result = apiRepository.getallPosts()
        result.body()?.let{
            allPosts.value = it
        }
    }

    fun getPostWithQuery(id:Int) = viewModelScope.launch {
        apiRepository.getPostWithQuery(id)
    }

    fun getPostWithPath(id:Int) = viewModelScope.launch {
        val response = apiRepository.getPostWithPath(id)
        if(response.isSuccessful){
            responseForId.value = response.body()
        }
    }

    fun insertPostWithFormUrl(
        id:Int,
        userId :Int,
        title : String,
        body : String
    ) = viewModelScope.launch {
        apiRepository.insertPostWithFormUrlEncoded(id,userId,title,body)
    }
}