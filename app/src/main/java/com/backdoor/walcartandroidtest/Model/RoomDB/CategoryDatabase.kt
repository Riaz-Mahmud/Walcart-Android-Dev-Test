package com.backdoor.walcartandroidtest.Model.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CategoryDataEntity::class], version = 1)
abstract class CategoryDatabase : RoomDatabase(){

    abstract fun getDao(): CategoryDao

    companion object{
        private var INSTANCE : CategoryDatabase? = null

        fun getDatabase(context : Context) : CategoryDatabase {
            var tempInstance : CategoryDatabase? = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this) {
                return Room.databaseBuilder(
                    context,
                    CategoryDatabase::class.java,
                    "walcat_db"
                ).build()
            }
        }
    }

}