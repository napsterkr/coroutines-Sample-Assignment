package com.example.myassignment

import com.example.myassignment.interfaces.ToDoApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {


    fun getApiService(): ToDoApi {

        val gson = provideGson()
        val retrofitBuilder = provideRetrofitBuilder()

        return retrofitBuilder
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(ToDoApi::class.java)
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(interceptor)
        return b.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    private fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

}
