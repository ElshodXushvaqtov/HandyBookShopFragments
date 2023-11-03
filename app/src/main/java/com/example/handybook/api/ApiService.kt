package com.example.handybook.api

import com.example.handybook.module.BookData
import com.example.handybook.module.Books
import com.example.handybook.module.Login
import com.example.handybook.module.User
import com.example.handybook.module.UserToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/book-api/register")
    fun register(@Body user: User): Call<UserToken>

    @POST("/book-api/login")
    fun login(@Body login: Login): Call<UserToken>

    @GET("/book-api")
    fun allBooks():Call<Books>

    @GET("/book-api/view")
    fun getBook(@Query("id") id : Int):Call<BookData>

    @GET("/book-api/all-category")
    fun getCategories()


}