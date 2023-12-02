package com.example.api_patch1

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query



interface RetrofitAPI {
    @GET("/posts")
    suspend fun getAllposts(): Response<List<Posts>>

    @GET("/posts")
    suspend fun getPostWithQuery(@Query("id") id :Int) : Response<List<Posts>>

    @GET("/posts/{id}")
    suspend fun getPostWithPath(@Path("id") id :Int) : Response<Posts>


    @POST("/posts")
    suspend fun insertPost(@Body posts: Posts):Response<Posts>

    @FormUrlEncoded
    @POST("/posts")
    suspend fun insertPost2(
        @Field("id") id: Int,
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body:String
    ):Response<Posts>
}
private val BASE_URL ="https://jsonplaceholder.typicode.com"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object APIs{
    val api : RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
}

