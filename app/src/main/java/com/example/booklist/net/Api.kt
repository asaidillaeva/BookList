package com.example.booklist.net

import com.example.booklist.model.Books
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("books/v1/volumes")
    fun getBooks(@Query("q") query: String): Call<Books>
}