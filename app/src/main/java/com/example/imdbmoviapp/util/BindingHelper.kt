package com.example.imdbmoviapp.util

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imdbmoviapp.R

class BindingHelper {

    companion object{

        @JvmStatic
        @BindingAdapter("setPosterImage")
        fun setPosterImage(imageView: ImageView, imagePath: String?) {
            val context = imageView.context
            Glide.with(context)
                .load(imagePath)
                .apply(
                    RequestOptions().centerInside().error(R.drawable.ic_gallery_default).placeholder(
                        R.drawable.ic_gallery_default
                    )
                )
                .into(imageView)
        }
    }
}