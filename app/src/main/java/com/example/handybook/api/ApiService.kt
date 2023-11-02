package com.example.handybook.api

import com.example.handybook.module.Login
import com.example.handybook.module.User
import com.example.handybook.module.UserToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/book-api/register")
    fun register(@Body user: User): Call<UserToken>

    @POST("/book-api/login")
    fun login(@Body login: Login): Call<UserToken>

}