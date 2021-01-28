package com.vipuldamor87.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.vipuldamor87.paging.data.RecyclerData
import com.vipuldamor87.paging.databinding.RecyclerviewRowBinding

class RecyclerViewAdapter :PagedListAdapter<RecyclerData, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    class MyViewHolder(val binding: RecyclerviewRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: RecyclerData)
        {
            binding.recyclerData = data
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
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
