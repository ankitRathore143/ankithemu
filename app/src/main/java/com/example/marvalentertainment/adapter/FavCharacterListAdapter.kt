package com.example.marvalentertainment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.marvalentertainment.R
import com.example.marvalentertainment.databinding.CharacterslistItemBinding
import com.example.marvalentertainment.model.FavCharactersModel
import com.example.marvalentertainment.model.Result
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.Flow

class FavCharacterListAdapter : RecyclerView.Adapter<FavCharacterListAdapter.MainViewHolder>() {

    var charachterlist = mutableListOf<FavCharactersModel>()

    fun setfavCharachter(movies: List<FavCharactersModel>) {
        this.charachterlist = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterslistItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val charachterlist = charachterlist.get(position)

        holder.binding.characterNameitemTv.text = charachterlist.name
        holder.binding.realNameitemTv.text = charachterlist.name

        Log.e(
            "Image",
            charachterlist.thumbnail.path.replace(
                "http:",
                "https:"
            ) + "." + charachterlist.thumbnail.extension
        )
        ;
        Picasso.get()
            .load(
                charachterlist.thumbnail.path.replace(
                    "http:",
                    "https:"
                ) + "." + charachterlist.thumbnail.extension
            )
            .placeholder(R.mipmap.marvellogo)
            .into(holder.binding.characteritemImg)


        holder.binding.characteritemImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // Do some work here
                val bundle = bundleOf(
                    "name" to charachterlist.name,
                    "overview" to charachterlist.description,
                    "resourceURI" to charachterlist.resourceURI,
                    "posterimage" to charachterlist.thumbnail.path.replace(
                        "http:",
                        "https:"
                    ) + "." + charachterlist.thumbnail.extension,
                    "modified" to charachterlist.modified

                )
                Navigation.findNavController(view).navigate(R.id.action_first_to_second, bundle);
            }

        })
    }

    override fun getItemCount(): Int {
        return charachterlist.size

    }


    class MainViewHolder(val binding: CharacterslistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}