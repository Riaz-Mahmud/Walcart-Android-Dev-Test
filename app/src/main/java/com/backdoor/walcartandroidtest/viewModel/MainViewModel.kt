package com.backdoor.walcartandroidtest.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.backdoor.walcartandroidtest.R

@SuppressLint("StaticFieldLeak")
class MainViewModel constructor(private var activity: Activity?) : ViewModel() {

    fun checkNightMode() {
        Log.d("dayNightMode", "Called")
        val nightModeFlags =
            activity!!.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.d("dayNightMode", "UI_MODE_NIGHT_YES")
                activity!!.window.statusBarColor = ContextCompat.getColor(activity!!, R.color.black_status_bar)
                val decorView = activity!!.window.decorView //set status background black
                decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() //set status text  light
                decorView.systemUiVisibility
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.d("dayNightMode", "UI_MODE_NIGHT_NO")
                activity!!.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
                activity!!.window.statusBarColor = ContextCompat.getColor(
                    activity!!,
                    R.color.white_status_bar
                ) // set status background white
            }
        }
    }
}