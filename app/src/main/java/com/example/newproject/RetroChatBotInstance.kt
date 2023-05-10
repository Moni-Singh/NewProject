package com.example.newproject

import android.util.Log
import com.example.newproject.`interface`.MyInterface
import com.example.newproject.utility.constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

object RetroChatBotInstance {

    private val mTag = "RetrofitRequest"
    private fun <S> createService(serviceClass: Class<S>): S? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build()

        return try {
            val retrofit: Retrofit? = Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create()
            )
                ?.baseUrl(constant.url)
//                ?.baseUrl("http://172.16.12.35:9000/api/")
                ?.client(okHttpClient)
                ?.build()
            retrofit?.create(serviceClass)
        } catch (e: IllegalArgumentException) {
            // this exception when bad url used
            Log.e(mTag, "Error is  :: " + e.message)
            null
        } catch (e: Exception) {
            Log.e(mTag, "Error is  :: " + e.message)
            null
        }
    }

    private var apiInterface: MyInterface? = null
    val myInterface: MyInterface?
        get() {
            if (apiInterface == null) {
                apiInterface =
                    createService(MyInterface::class.java)
            }
            return apiInterface
        }

}