package com.example.marvalentertainment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvalentertainment.R
import com.example.marvalentertainment.databinding.CharacterslistItemBinding
import com.example.marvalentertainment.model.Result
import com.example.marvalentertainment.utils.CellClickListener
import com.squareup.picasso.Picasso


//TODO Ankit Rathore 46143234

class CharactersAdapter(private val cellClickListener: CellClickListener) :
    PagingDataAdapter<Result, CharactersAdapter.MainViewHolder>(CharactersComparator) {

    var charachterlist = mutableListOf<Result>()
    fun setMovies(characterslist: List<Result>) {
        this.charachterlist = characterslist.toMutableList()

        notifyDataSetChanged()
    }

    object CharactersComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.characterId == newItem.characterId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterslistItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val charachterObject = charachterlist.get(holder.absoluteAdapterPosition)


        holder.binding.characterNameitemTv.text = charachterObject.name
        holder.binding.realNameitemTv.text = charachterObject.name

        Picasso.get()
            .load(
                charachterObject.thumbnail?.path?.replace(
                    "http:",
                    "https:"
                ) + "." + charachterObject.thumbnail?.extension
            )
            .placeholder(R.mipmap.marvellogo)
            .into(holder.binding.characteritemImg)

        if (charachterObject.isValid == true) {
            holder.binding.navFavourite.visibility = View.VISIBLE
            holder.binding.relativelayout.setBackgroundResource(R.drawable.round_red_backround)
        } else {
            holder.binding.navFavourite.visibility = View.GONE
            holder.binding.relativelayout.setBackgroundResource(R.drawable.round_black_background)
        }


        holder.binding.characteritemImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Log.e("DataAdapter", charachterObject.isValid.toString())
                if (charachterObject.isValid == true) {
                    holder.binding.navFavourite.visibility = View.GONE
                    charachterObject.isValid = false
                    Toast.makeText(view.context, "Remove from Favourite", Toast.LENGTH_SHORT).show()
                    holder.binding.relativelayout.setBackgroundResource(R.drawable.round_black_background)
                    cellClickListener.onCellClickListener(
                        listOf(
                            charachterObject
                        )
                    )
                } else if (charachterObject.isValid == false || charachterObject.isValid == null) {
                    Toast.makeText(view.context, "Add to Favourite", Toast.LENGTH_SHORT).show();
                    holder.binding.navFavourite.visibility = View.VISIBLE
                    holder.binding.relativelayout.setBackgroundResource(R.drawable.round_red_backround)
                    charachterObject.isValid = true
                    cellClickListener.onCellClickListener(
                        listOf(
                            charachterObject
                        )
                    )
                } else {
                    holder.binding.navFavourite.visibility = View.INVISIBLE
                    holder.binding.relativelayout.setBackgroundResource(R.drawable.round_black_background)

                }
            }
        })

        holder.binding.linearlayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // Do some work here
                val bundle = bundleOf(
                    "name" to charachterObject.name,
                    "overview" to charachterObject.description,
                    "posterimage" to charachterObject.thumbnail?.path?.replace(
                        "http:",
                        "https:"
                    ) + "." + charachterObject.thumbnail?.extension,
                    "modified" to charachterObject.modified

                )
                Navigation.findNavController(view)
                    .navigate(R.id.action_first_to_second, bundle);
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