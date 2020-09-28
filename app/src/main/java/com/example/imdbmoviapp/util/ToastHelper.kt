package com.example.imdbmoviapp.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.imdbmoviapp.R

object ToastHelper {
    fun showToast(context: Context, msg: String?) {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.toast_layout, null)
        val text = layout.findViewById<View>(R.id.toastMessage) as TextView
        text.text = msg
        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}