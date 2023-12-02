package com.example.api_patch1

import com.squareup.moshi.Json

data class Posts(
    @Json(name ="id")val id: Int,
    @Json(name="userId") val userId:Int,
    @Json(name = "title") val title :String,
    @Json(name = "body") val body: String
) {
}