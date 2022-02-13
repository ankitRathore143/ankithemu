package com.example.marvalentertainment.retrofit


//TODO Ankit Rathore 46143234

class ApiHelper constructor(private val apiService: RetrofitService) {
    suspend fun getMovieData() = apiService.getMovieList()



}