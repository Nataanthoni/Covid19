package com.kweracodes.covid19

import com.kweracodes.covid19.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient


object RetrofitClient {
    private const val BASE_URL = Constants.BASE_URL


    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)


        }.build()

    //Allows the late conversion of malformed Json to GSON
    val instance: Api by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …

        // add logging as last interceptor
        retrofit.create(Api::class.java)

    }


}