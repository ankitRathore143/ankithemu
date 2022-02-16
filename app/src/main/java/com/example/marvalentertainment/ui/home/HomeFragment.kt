package com.example.marvalentertainment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvalentertainment.BuildConfig
import com.example.marvalentertainment.adapter.CharactersAdapter
import com.example.marvalentertainment.databinding.FragmentHomeBinding
import com.example.marvalentertainment.model.MainViewModel
import com.example.marvalentertainment.model.MyViewModelFactory
import com.example.marvalentertainment.model.Result
import com.example.marvalentertainment.retrofit.ApiHelper
import com.example.marvalentertainment.retrofit.RetrofitService
import com.example.marvalentertainment.ui.favourite.FavViewModelFactory
import com.example.marvalentertainment.ui.favourite.FavouriteViewModel
import com.example.marvalentertainment.utils.CellClickListener
import com.example.marvalentertainment.utils.CharaterApplication

class HomeFragment : Fragment(), CellClickListener {

    lateinit var viewModel: MainViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val favouriteViewModel: FavouriteViewModel by viewModels {

        FavViewModelFactory((activity?.application as CharaterApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate the layout and bind to the _binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val retrofitService = RetrofitService.getInstance(BuildConfig.BASE_URL)
        val mainRepository = ApiHelper(retrofitService)

        val adapter = CharactersAdapter(this)

        val linearLayoutManager = LinearLayoutManager(activity)
        val layoutManager = GridLayoutManager(activity, 2);

        // at last set adapter to recycler view.
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.movielistActmain!!.layoutManager = layoutManager

        binding.movielistActmain.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        viewModel.movieList.observe(viewLifecycleOwner) {

            favouriteViewModel.insert(it)

            binding.lodingTv.visibility = View.GONE
            binding.movielistActmain.visibility = View.VISIBLE
        }

        favouriteViewModel.getdatagav.observe(viewLifecycleOwner) {
            adapter.setMovies(it)

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getAllMovies()
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


    override fun onCellClickListener(resultlist: List<Result>) {
        favouriteViewModel.update(resultlist)

    }


}