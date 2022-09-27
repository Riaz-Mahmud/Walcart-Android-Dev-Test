package com.backdoor.walcartandroidtest.View.Fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backdoor.walcartandroidtest.Model.CategoryRepository
import com.backdoor.walcartandroidtest.viewModel.CategoryViewModel

class MyViewModelFactory constructor(private val repository: CategoryRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            CategoryViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
