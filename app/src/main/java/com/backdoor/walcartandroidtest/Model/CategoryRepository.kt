package com.backdoor.walcartandroidtest.Model

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.GetCategoriesListQuery

class CategoryRepository constructor(private val apolloClient: ApolloClient) {

    suspend fun getAllRootCat() : List<GetCategoriesListQuery.Category>? {

        val response = try {
            apolloClient.query(GetCategoriesListQuery()).execute()
        } catch (e: ApolloException) {
            Log.d("LaunchList", "Failure", e)
            null
        }
        val launches = response?.data?.getCategories?.result?.categories?.filterNotNull()
        return launches
    }

}