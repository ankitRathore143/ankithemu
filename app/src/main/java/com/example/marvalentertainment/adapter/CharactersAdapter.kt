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
import com.example.marvalentertainment.model.Result
import com.squareup.picasso.Picasso
import java.util.*


//TODO Ankit Rathore 46143234

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.MainViewHolder>() {

    var charachterlist = mutableListOf<Result>()
    fun setMovies(movies: List<Result>) {
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


        Picasso.get()
            .load(
                charachterlist.thumbnail.path.replace(
                    "http:",
                    "https:"
                ) + "." + charachterlist.thumbnail.extension
            )
            .placeholder(R.mipmap.marvellogo)
            .into(holder.binding.characteritemImg)

        holder.binding.favouriteImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                holder.binding.relativelayout.setBackgroundResource(R.drawable.round_red_backround)
                holder.binding.favouriteImg.setImageResource(R.drawable.ic_selected_favorite_rate_star_icon)

            }
        })

        holder.binding.linearlayout.setOnClickListener(object : View.OnClickListener {
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