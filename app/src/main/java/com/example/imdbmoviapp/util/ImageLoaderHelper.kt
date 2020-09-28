package com.example.imdbmoviapp.util

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.imdbmoviapp.R


object ImageLoaderHelper {

    private var options: RequestOptions? = null

    fun loadImage(
        context: Context,
        url: String?,
        view: ImageView
    ) {
        if (options == null) {
            options = RequestOptions()
                .placeholder(R.drawable.ic_gallery_default)
                .error(R.drawable.ic_gallery_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        }


        if (url == null || url.equals("", ignoreCase = true)) {
            view.setImageDrawable(
                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ic_gallery_default
                )
            )
        } else {
            Glide.with(view).applyDefaultRequestOptions(options!!).load(url).into(view)
        }
    }

}