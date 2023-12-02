package com.example.api_patch1.repository

import com.example.api_patch1.APIs
import com.example.api_patch1.Posts
//import com.example.api_patch1.retrofitapi
import retrofit2.Response

class ApiRepository {


    suspend fun  getallPosts() : Response<List<Posts>> = APIs.api.getAllposts()

    suspend fun  getPostWithPath(id:Int) : Response<Posts> = APIs.api.getPostWithPath(id)

    suspend fun getPostWithQuery(id:Int): Response<List<Posts>> = APIs.api.getPostWithQuery(id)

    suspend fun insertPost(posts: Posts) : Response<Posts> = APIs.api.insertPost(posts)

    suspend fun insertPostWithFormUrlEncoded(
        id:Int,
        userId :Int,
        title : String,
        body : String
    ) : Response<Posts> = APIs.api.insertPost2(id,userId,title,body)

}