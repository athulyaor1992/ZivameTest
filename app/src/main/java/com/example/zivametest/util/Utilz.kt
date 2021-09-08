package com.example.zivametest.util

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.zivametest.R
import com.google.android.material.snackbar.Snackbar


fun View.snackbar(context: Context, message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.
        setAction("Ok") {
            snackbar.dismiss()
        }.view.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
    }.
    show()

}


