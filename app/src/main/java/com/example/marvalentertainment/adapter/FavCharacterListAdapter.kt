package com.example.marvalentertainment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.marvalentertainment.R
import com.example.marvalentertainment.databinding.CharacterslistItemBinding
import com.example.marvalentertainment.databinding.FavouritecharItemBinding
import com.example.marvalentertainment.model.FavCharactersModel
import com.example.marvalentertainment.model.Result
import com.example.marvalentertainment.utils.CellClickListener
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.Flow

class FavCharacterListAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<FavCharacterListAdapter.MainViewHolder>() {

    var charachterlist = mutableListOf<Result>()

    fun setfavCharachter(favlist: List<Result>) {
        this.charachterlist = favlist.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavouritecharItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val charachterlist = charachterlist.get(position)


        holder.binding.favcharacteritemImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                charachterlist.isValid = false
                Toast.makeText(view.context, "Remove from Favourite", Toast.LENGTH_SHORT).show()
                holder.binding.relativelayout.setBackgroundResource(R.drawable.round_black_background)
                cellClickListener.onCellClickListener(
                    listOf(
                        charachterlist
                    )
                )
            }
        })


        if (charachterlist.isValid == true) {
            holder.binding.cardview.visibility = View.VISIBLE
            holder.binding.favcharacterNameitemTv.text = charachterlist.name
            holder.binding.favrealNameitemTv.text = charachterlist.name
            Log.e("FavouriteData", charachterlist.isValid.toString())
            Picasso.get()
                .load(
                    charachterlist.thumbnail?.path?.replace(
                        "http:",
                        "https:"
                    ) + "." + charachterlist.thumbnail?.extension
                )
                .placeholder(R.mipmap.marvellogo)
                .into(holder.binding.favcharacteritemImg)
        } else {
            holder.binding.cardview.visibility = View.GONE
        }


    }

    override fun getItemCount(): Int {
        return charachterlist.size

    }


    class MainViewHolder(val binding: FavouritecharItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}