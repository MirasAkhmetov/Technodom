package com.project.technodom.global.di

import com.project.technodom.BuildConfig
import com.project.technodom.global.di.ServiceProperties.API_KEY
import com.project.technodom.global.service.ServerService
import com.project.technodom.global.di.ServiceProperties.SERVER_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit




val networkModule = module {
    single { createOkHttpClient() }
    single { createWebService<ServerService>(get(), SERVER_URL) }
}


object ServiceProperties {
    const val SERVER_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "6e63c2317fbe963d76c3bdc2b785f6d1"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
}

fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    okHttpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
    okHttpClientBuilder.callTimeout(5, TimeUnit.MINUTES)
    okHttpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
    okHttpClientBuilder.writeTimeout(5, TimeUnit.MINUTES)

    okHttpClientBuilder.addInterceptor { chain ->
        val url = chain.request().url().newBuilder().addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request().newBuilder().url(url).build()

        chain.proceed(request)
    }

    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
