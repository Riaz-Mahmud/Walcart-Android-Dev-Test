package com.backdoor.walcartandroidtest.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryDataEntity(

    @PrimaryKey val uid: String,
    @ColumnInfo(name = "enName") val enName: String?,
    @ColumnInfo(name = "bnName") val bnName: String?,
    @ColumnInfo(name = "attributeSetUid") val attributeSetUid: String?,
    @ColumnInfo(name = "isActive") val isActive: Boolean?,
    @ColumnInfo(name = "inActiveNote") val inActiveNote: String?,

    )
