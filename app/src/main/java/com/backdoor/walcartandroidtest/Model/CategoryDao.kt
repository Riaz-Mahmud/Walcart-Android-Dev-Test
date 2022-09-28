package com.backdoor.walcartandroidtest.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert
    fun data_insert(data : CategoryDataEntity)

    @Query("SELECT * FROM category_table ORDER BY uid")
    fun get_all_category(): List<CategoryDataEntity>

    @Delete
    fun delete(data: CategoryDataEntity)
}