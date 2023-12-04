package com.example.api_patch1

import okhttp3.Interceptor
import okhttp3.Response


//Adds all Headers to all the requests
class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-type","application/json")
            .addHeader("X-Platform","Android")
            .addHeader("X-Auth-Token","1234")
            .build()
        return chain.proceed(request)
    }
}