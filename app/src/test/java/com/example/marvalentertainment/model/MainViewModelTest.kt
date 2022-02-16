package com.example.marvalentertainment.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.marvalentertainment.retrofit.ApiHelper
import com.example.marvalentertainment.retrofit.RetrofitService
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)

class MainViewModelTest : TestCase() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var apihelper: ApiHelper

    @Mock
    private lateinit var apiUsersObserver: Observer<List<Result>>

    @Before
    public override fun setUp() {
        super.setUp()

        val retrofitService = RetrofitService.getInstance(API.baseurl)
        apihelper = ApiHelper(retrofitService)
        mainViewModel = MainViewModel(apihelper)
    }

    @Test
    fun checkDataisEmptyorNot() {
        mainViewModel.getAllMovies()
        assertTrue(mainViewModel.movieList.value?.size != 0)


    }

}