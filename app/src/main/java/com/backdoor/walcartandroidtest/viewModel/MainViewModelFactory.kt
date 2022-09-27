package com.backdoor.walcartandroidtest.viewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(
    private val activity: Activity?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
