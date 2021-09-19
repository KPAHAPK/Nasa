package com.example.nasaapp.repository.retrofit

import com.example.nasaapp.repository.responsedata.PODServerResponseData
import com.example.nasaapp.repository.responsedata.SolarFlareServerResponseData
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PODRetrofitImpl {


    private val baseUrl = "https://api.nasa.gov/"

    private val api by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build().create(RetrofitApi::class.java)
    }

    fun getPOD(date: String, apiKey: String, podCallBack: Callback<PODServerResponseData>) {
        api.getPOD(apiKey, date).enqueue(podCallBack)
    }

    fun getSolarFlareToday(
        startDate: String,
        apiKey: String,
        podCallBack: Callback<List<SolarFlareServerResponseData>>
    ) {
        api.getSolarFlareToday(apiKey, startDate).enqueue(podCallBack)
    }
}
