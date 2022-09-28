package com.backdoor.walcartandroidtest.View.Fragment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.backdoor.walcartandroidtest.R

class HomeFragment : Fragment() {

    private lateinit var viewOfLayout: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_home, container, false)

        val switch = viewOfLayout.findViewById<Switch>(R.id.switch1)

        when (requireActivity().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                switch.isChecked = true
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                switch.isChecked = false
            }
        }

        switch.setOnClickListener {
            Log.d("SwitchCheck", "value: " + switch.isChecked)
            if (switch.isChecked) {
                nightModeOn()
            } else {
                dayModeOn()
            }

        }

        return viewOfLayout
    }

    private fun dayModeOn() {
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white_status_bar)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun nightModeOn() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.black_status_bar)
        val decorView = requireActivity().window.decorView //set status background black
        decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() //set status text  light
        decorView.systemUiVisibility
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

}