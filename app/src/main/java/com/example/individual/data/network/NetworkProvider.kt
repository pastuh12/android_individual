package com.example.individual.data.network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkProvider(context: Context) {

    private val gson =
        GsonBuilder()
            .create()

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor(ApiLogger()).apply { level = HttpLoggingInterceptor.Level.BODY }

    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)

    val individualApi: IndividualApi = retrofitBuilder
        .client(okHttpClientBuilder.build())
        .build()
        .create(IndividualApi::class.java)

    val individualApiMock = IndividualApiMock()

    companion object {
        private val BASE_URL = "https://androidindividual.onrender.com/"
        private var INSTANCE: NetworkProvider? = null
        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NetworkProvider(context)
            }
        }

        fun get(): NetworkProvider {
            return INSTANCE!!
        }
    }
}