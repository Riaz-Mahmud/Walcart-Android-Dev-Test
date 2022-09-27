package com.backdoor.walcartandroidtest.viewModel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backdoor.walcartandroidtest.Model.CategoryRepository

class CategoryViewModelFactory(
    private val activity: FragmentActivity?,
    private val repository: CategoryRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            CategoryViewModel(activity,this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
