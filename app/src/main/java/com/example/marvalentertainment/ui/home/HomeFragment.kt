package com.example.marvalentertainment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvalentertainment.adapter.CharactersAdapter
import com.example.marvalentertainment.databinding.FragmentHomeBinding
import com.example.marvalentertainment.model.MainViewModel
import com.example.marvalentertainment.model.MyViewModelFactory
import com.example.marvalentertainment.retrofit.API
import com.example.marvalentertainment.retrofit.ApiHelper
import com.example.marvalentertainment.retrofit.RetrofitService

class HomeFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private val adapter = CharactersAdapter()
    private var _binding: FragmentHomeBinding? = null

    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate the layout and bind to the _binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val retrofitService = RetrofitService.getInstance(API.baseurl)
        val mainRepository = ApiHelper(retrofitService)

        val linearLayoutManager = LinearLayoutManager(activity)
        val layoutManager = GridLayoutManager(activity, 2);

        // at last set adapter to recycler view.
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.movielistActmain!!.layoutManager = layoutManager


//        showHide(binding.movielistActmain)
        binding.movielistActmain.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)


        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMovies(it)
//            binding.lodingTv.visibility = View.INVISIBLE
            binding.movielistActmain.visibility = View.VISIBLE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getAllMovies()


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }
}