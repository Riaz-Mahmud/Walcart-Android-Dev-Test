package com.backdoor.walcartandroidtest.Model.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert
    fun data_insert(vararg data : CategoryDataEntity)

    @Query("SELECT * FROM category_table")
    fun get_all_category(): List<CategoryDataEntity>

    @Delete
    fun delete(data: CategoryDataEntity)
}