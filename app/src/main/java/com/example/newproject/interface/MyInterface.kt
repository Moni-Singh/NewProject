package com.example.newproject.`interface`

import android.content.Context
import com.example.newproject.model.Login
import com.example.newproject.model.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyInterface {


    @Headers("Content-Type: application/json")
    @POST("register")
    fun registeruser(@Body register: Register?) : Call<Register?>


    @Headers("Context-Type: application/json")
    @POST("login")
    fun loginuser(@Body login : Login?) : Call<Login?>
}