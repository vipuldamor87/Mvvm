package com.vipuldamor87.mvvmsample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vipuldamor87.mvvmsample.data.db.entities.CURRENT_USER_ID
import com.vipuldamor87.mvvmsample.data.db.entities.User

interface UserDao{
     @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User) : Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser() :LiveData<User>
}