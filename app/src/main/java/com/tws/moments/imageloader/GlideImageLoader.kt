package com.tws.moments.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tws.moments.R

class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun displayImage(url: String?, imageView: ImageView) {
        if (url.isNullOrEmpty()) return

        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}