package com.backdoor.walcartandroidtest.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backdoor.walcartandroidtest.Model.CategoryRepository
import com.example.GetCategoriesListQuery
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class CategoryViewModel constructor(private var activity: FragmentActivity?, private val catRepository: CategoryRepository) : ViewModel() {

    val categoriesList = MutableLiveData<List<GetCategoriesListQuery.Category>?>()

    fun onBackBtnPress() {
        activity!!.onBackPressed()
    }

    fun getAllCategories() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            val response = catRepository.getAllRootCat()

            categoriesList.postValue(response)

        }
    }

}