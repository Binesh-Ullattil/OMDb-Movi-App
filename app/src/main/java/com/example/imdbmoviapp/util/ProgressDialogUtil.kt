package com.example.imdbmoviapp.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import com.example.imdbmoviapp.R

object ProgressDialogUtil {

    private var pd: ProgressDialog? = null

    fun showProgressDialog(
        context: Context?,
        is_cancelable: Boolean
    ) {
        dismissProgressDialog()
        if (context != null) {
            pd =
                ProgressDialog(context, R.style.MyTheme)
            pd!!.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
            pd!!.setCancelable(is_cancelable)
            pd!!.show()
        }
    }

     fun dismissProgressDialog() {
        if (null != pd && pd!!.isShowing) {
            pd!!.cancel()
            pd = null
        }
    }
}