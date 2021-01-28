package com.vipuldamor87.paging.data

data class RecyclerList(
    val data : ArrayList<RecyclerData>
)
data class RecyclerData(
    val id:Int,
    val email:String,
    val first_name:String,
    val last_name:String,
    val avatar:String
)