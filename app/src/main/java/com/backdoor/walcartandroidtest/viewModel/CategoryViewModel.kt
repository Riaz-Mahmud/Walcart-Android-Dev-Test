package com.backdoor.walcartandroidtest.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backdoor.walcartandroidtest.Model.CategoryRepository
import com.example.GetCategoriesListQuery
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class CategoryViewModel constructor(
    private var activity: FragmentActivity?,
    private val catRepository: CategoryRepository
) : ViewModel() {

    val categoriesList = MutableLiveData<List<GetCategoriesListQuery.Category>?>()
    val errorMessage = MutableLiveData<String>()


    fun onBackBtnPress() {
        activity!!.onBackPressed()
    }

    fun getAllCategories() {
        Log.d("Thread Outside", Thread.currentThread().name)
        if (checkConnection()) {
            viewModelScope.launch {
                Log.d("Thread Inside", Thread.currentThread().name)
                val response = catRepository.getAllRootCat()
                if(response == null){
                    errorMessage.value= "Something going wrong! Please try again"
                }
                categoriesList.postValue(response)

            }
        }
    }

    private fun checkConnection(): Boolean {
        val manager =
            (activity!!.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetwork = manager.activeNetworkInfo
        return if (activeNetwork == null) {
            errorMessage.value = "No Internet Connection"
            false
        } else {
            true
        }
    }


}