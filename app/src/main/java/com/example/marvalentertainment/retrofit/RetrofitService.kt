package com.example.marvalentertainment.retrofit

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.example.marvalentertainment.model.DataModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

//TODO Ankit Rathore 46143234

interface RetrofitService {


//    @GET(API.characters + API.publicKey + "&hash")

    @GET("v1/public/characters?")
    suspend fun getMovieList(): Response<DataModel>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(baseurl: String): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .client(okHttpClientBuilder)
//                    .client(RetrofitNetwork.getOkHttpClient(true))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }


        private val authInterceptor = { chain: Interceptor.Chain ->
            val ts = System.currentTimeMillis()

            val hash =
                "$ts${API.privateKey}${API.publicKey}".encryptmd5()

            val request = chain.request()

            val url = request.url()
                .newBuilder()
                .addQueryParameter("limit", "80")
                .addQueryParameter("ts", ts.toString())
                .addQueryParameter("apikey", API.publicKey)
                .addQueryParameter("hash", hash)
                .build()
            Log.e("RetrofitService", "requestURL=$url")

            val updated = request.newBuilder()
                .url(url)
                .build()

            chain.proceed(updated)
        }


        private val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()


        private fun String.encryptmd5(): String =
            BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
                .toString(16).padStart(32, '0')
    }
}