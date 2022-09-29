package com.backdoor.walcartandroidtest.Model.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDataEntity(

    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "uid")
    val uid: String,
    @ColumnInfo(name = "enName")
    val enName: String?,
    @ColumnInfo(name = "bnName")
    val bnName: String?,
    @ColumnInfo(name = "attributeSetUid")
    val attributeSetUid: String?,
    @ColumnInfo(name = "isActive")
    val isActive: String?,
    @ColumnInfo(name = "inActiveNote")
    val inActiveNote: String?,

    )
