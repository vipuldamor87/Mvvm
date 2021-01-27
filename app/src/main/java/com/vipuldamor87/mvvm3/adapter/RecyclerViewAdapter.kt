package com.vipuldamor87.mvvm3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vipuldamor87.mvvm3.R
import com.vipuldamor87.mvvm3.data.RecyclerData
import com.vipuldamor87.mvvm3.databinding.RecyclerviewRowBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var items = ArrayList<RecyclerData>()

    fun setDataList(data : ArrayList<RecyclerData>){
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class MyViewHolder(val binding: RecyclerviewRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: RecyclerData)
        {
            binding.recyclerData = data
            binding.executePendingBindings()
        }
    }
    companion object{
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, avatar :String){
            Glide.with(imageView)
                .load(avatar)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}