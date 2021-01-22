package com.vipuldamor87.mvvmsample.data.network.responses

import com.vipuldamor87.mvvmsample.data.db.entities.User

data class AuthResponse(
        val isSuccesful : Boolean?,
        val message: String?,
        val user: User?,
        )