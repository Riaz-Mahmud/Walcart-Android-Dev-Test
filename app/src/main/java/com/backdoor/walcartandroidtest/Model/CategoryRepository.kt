package com.backdoor.walcartandroidtest.Model

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.backdoor.walcartandroidtest.Model.RoomDB.CategoryDataEntity
import com.backdoor.walcartandroidtest.View.Activity.MainActivity
import com.example.GetCategoriesListQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryRepository constructor(private val apolloClient: ApolloClient) {
    suspend fun getAllRootCat(): List<GetCategoriesListQuery.Category>? {

        val response = try {
            apolloClient.query(GetCategoriesListQuery()).execute()
        } catch (e: ApolloException) {
            Log.d("LaunchList", "Failure", e)
            null
        }

        if (response?.data?.getCategories?.statusCode == 200) {
            val launches = response.data?.getCategories?.result?.categories?.filterNotNull()
            saveRoomDB(launches)
            return launches
        }
        return null
    }

    private fun saveRoomDB(launches: List<GetCategoriesListQuery.Category>?) {

        val catDao = MainActivity().categoryDao

        GlobalScope.launch {
            if (launches != null) {
                for (item in launches) {
                    catDao?.insertCategory(
                        CategoryDataEntity(
                            0, item.uid.toString(), item.enName, item.bnName, item.attributeSetUid,
                            item.isActive.toString(), item.inActiveNote
                        )
                    )
                    Log.d("RoomDBCat", "Data Store: " + item.uid)
                }
            }
        }
    }

}