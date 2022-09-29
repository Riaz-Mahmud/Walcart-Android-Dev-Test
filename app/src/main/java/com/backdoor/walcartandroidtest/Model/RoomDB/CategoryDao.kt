package com.backdoor.walcartandroidtest.Model.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(data: CategoryDataEntity)

    @Query("SELECT * FROM category")
    fun getAllCategory(): LiveData<List<CategoryDataEntity>>

    @Delete
    suspend fun delete(data: CategoryDataEntity)
}