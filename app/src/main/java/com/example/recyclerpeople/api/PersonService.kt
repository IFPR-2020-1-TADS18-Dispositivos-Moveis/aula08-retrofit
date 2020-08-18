package com.example.recyclerpeople.api

import com.example.recyclerpeople.model.Person
import retrofit2.Call
import retrofit2.http.*

interface PersonService {
    // [GET] http://localhost:3000/people
    @GET("people")
    fun getAll(): Call<List<Person>>

    // [GET] http://localhost:3000/people/111
    @GET("people/{id}")
    fun get(@Path("id") id: Long): Call<Person>

    // [POST] http://localhost:3000/people
    @Headers("Content-Type: application/json")
    @POST("people")
    fun insert(@Body person: Person): Call<Person>

    // [PATCH] http://localhost:3000/people/111
    @Headers("Content-Type: application/json")
    @PATCH("people/{id}")
    fun update(@Path("id") id: Long, @Body person: Person): Call<Person>

    // [DELETE] http://localhost:3000/people/111
    @DELETE("people/{id}")
    fun delete(@Path("id") id: Long): Call<Void>
}