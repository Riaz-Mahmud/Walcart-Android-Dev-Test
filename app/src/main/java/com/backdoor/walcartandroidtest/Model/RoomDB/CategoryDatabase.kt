package com.backdoor.walcartandroidtest.Model.RoomDB

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [CategoryDataEntity::class], version = 1
)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun getDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        private const val DB_NAME = "walcat.db"
        private const val DB_FILENAME = "AppDB.db" //<<<<< ADDED for getting header
        const val TAG = "DBINFO" //<<<< ADDED for logging
        const val DBVERSION = 1 //<<<<<ADDED for logging

        fun getDatabase(context: Context): CategoryDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CategoryDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }

        object dbCallback : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d(TAG, "onCreate")
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.d(TAG, "onOpen")
            }

            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
                Log.d(TAG, "onDestructiveMigration")
            }
        }
    }


}