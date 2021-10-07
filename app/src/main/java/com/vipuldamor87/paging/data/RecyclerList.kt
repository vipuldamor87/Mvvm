package com.vipuldamor87.paging.data

data class RecyclerList(
    val data : ArrayList<RecyclerData>
)
data class RecyclerData(
    val _id:Int,
    val name:String,
    val trips:String,
)