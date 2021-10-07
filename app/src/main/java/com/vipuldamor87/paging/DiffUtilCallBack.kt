package com.vipuldamor87.paging

import androidx.recyclerview.widget.DiffUtil
import com.vipuldamor87.paging.data.RecyclerData

class DiffUtilCallBack : DiffUtil.ItemCallback<RecyclerData>() {
    override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
       return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
       return oldItem._id == newItem._id
    }
}