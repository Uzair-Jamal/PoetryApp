package com.example.poetryapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poetryapp.PoetryModel
import com.example.poetryapp.databinding.PoetryListDesignBinding

class PoetryAdapter(private var poetryList: List<PoetryModel>) : RecyclerView.Adapter<PoetryAdapter.PoetryViewHolder>() {

    inner class PoetryViewHolder(val binding: PoetryListDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetryViewHolder {
        return PoetryViewHolder(
            PoetryListDesignBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return poetryList.size
    }

    override fun onBindViewHolder(holder: PoetryViewHolder, position: Int) {
        holder.binding.tvPoetryData.text = poetryList[position].poetryData
        holder.binding.tvPoetName.text = poetryList[position].poetName
        holder.binding.tvPoetryDateTime.text = poetryList[position].dateTime
    }

    fun updateData(newPoetryList: List<PoetryModel>){
        poetryList = newPoetryList
        notifyDataSetChanged()
    }
}


