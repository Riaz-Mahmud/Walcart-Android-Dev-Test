package com.backdoor.walcartandroidtest.View.Model

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class PerfConfig(private val context: Context) {
    var loadingBar: ProgressDialog? = null
    var snackbar: Snackbar? = null
    fun displayToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun displayToastLong(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun displaySnackBar(view: View?, message: String?) {
        try {
            snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT)
            snackbar!!.setAction("OK") { snackbar!!.dismiss() }
            snackbar!!.show()
        } catch (ex: Exception) {
            Log.d("Error", "displaySnackBar Error: " + ex.message)
        }
    }

    fun loadingBar(context: Context?, message: String?) {
        loadingBar = ProgressDialog(context)
        loadingBar!!.setMessage(message)
        loadingBar!!.setCanceledOnTouchOutside(false)
        loadingBar!!.show()
    }
}