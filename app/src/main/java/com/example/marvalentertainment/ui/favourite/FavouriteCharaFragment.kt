package com.example.marvalentertainment.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marvalentertainment.adapter.FavCharacterListAdapter
import com.example.marvalentertainment.databinding.FragmentFavouriteBinding

class FavouriteCharaFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val adapter = FavCharacterListAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoViewModel =
            ViewModelProvider(this).get(FavouriteViewModel::class.java)

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.favouritelistFavo.adapter = adapter

        favoViewModel.listfav.observe(viewLifecycleOwner) {
            adapter.setfavCharachter(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}