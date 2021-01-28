package com.vipuldamor87.paging

import androidx.recyclerview.widget.DiffUtil
import com.vipuldamor87.paging.data.RecyclerData

class DiffUtilCallBack : DiffUtil.ItemCallback<RecyclerData>() {
    override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
       return oldItem.id == newItem.id
    }
}