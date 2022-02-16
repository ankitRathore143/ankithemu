package com.example.marvalentertainment.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvalentertainment.adapter.FavCharacterListAdapter
import com.example.marvalentertainment.databinding.FragmentFavouriteBinding
import com.example.marvalentertainment.utils.CharaterApplication

class FavouriteCharaFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val adapter = FavCharacterListAdapter()

    private val favoViewModel: FavouriteViewModel by viewModels {
        FavViewModelFactory((activity?.application as CharaterApplication).repository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val linearLayoutManager = LinearLayoutManager(activity)
        val layoutManager = GridLayoutManager(activity, 2);
        // at last set adapter to recycler view.
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.favouritelistFavo!!.layoutManager = layoutManager

        binding.favouritelistFavo.adapter = adapter

        favoViewModel.selectWords.observe(viewLifecycleOwner) {
            adapter.setfavCharachter(it)
            adapter.notifyDataSetChanged()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}