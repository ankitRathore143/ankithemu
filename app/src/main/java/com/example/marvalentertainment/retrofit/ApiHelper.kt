package com.example.marvalentertainment.retrofit


//TODO Ankit Rathore 46143234

class ApiHelper constructor(private val retrofitService: RetrofitService) {
    suspend fun getMovieData() = retrofitService.getMovieList()

}