package com.backdoor.walcartandroidtest.Model

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.backdoor.walcartandroidtest.Model.RoomDB.CategoryDataEntity
import com.backdoor.walcartandroidtest.View.Activity.MainActivity
import com.example.GetCategoriesListQuery

class CategoryRepository constructor(private val apolloClient: ApolloClient) {

    var datalist : List<CategoryDataEntity>? = null

    suspend fun getAllRootCat() : List<GetCategoriesListQuery.Category>? {

        val response = try {
            apolloClient.query(GetCategoriesListQuery()).execute()
        } catch (e: ApolloException) {
            Log.d("LaunchList", "Failure", e)
            null
        }
        val launches = response?.data?.getCategories?.result?.categories?.filterNotNull()
        saveRoomDB(launches)
        return launches
    }

    private fun saveRoomDB(launches: List<GetCategoriesListQuery.Category>?) {

        val catDao = MainActivity().database

        val insertThread = Thread {
            if (launches != null) {
                for (item in launches){
                    catDao?.data_insert(
                        CategoryDataEntity(
                            item.uid.toString(),item.enName,item.bnName,item.attributeSetUid,
                            item.isActive,item.inActiveNote)
                    )
                    Log.d("RoomDBCat", "Data Store: "+ item.uid)
                }
            }
        }
        insertThread.start()
    }

}