package com.example.marvalentertainment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvalentertainment.databinding.FragmentCharDetailsBinding
import com.squareup.picasso.Picasso

//TODO Ankit Rathore 46143234
class DetailsFragment : Fragment() {

    private var _binding: FragmentCharDetailsBinding? = null

    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharDetailsBinding.inflate(inflater, container, false)

        binding.titalNameTv.text = arguments?.getString("name").toString()
        // Inflate the layout for this fragment
        Picasso.get()
            .load(arguments?.getString("posterimage"))
            .into(binding.posterImg)
        binding.overviewmovieTv.text = arguments?.getString("overview")
        binding.releaseDateTv.text = arguments?.getString("releaseDate")
//        binding.voteTv.text = "Vote Average " + arguments?.getDouble("voteAverage")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}