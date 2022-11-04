package com.example.rickandmortyproject.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.example.rickandmortyproject.R
import com.squareup.picasso.Picasso

fun loadImage(url: String, view: ImageView, context: Context) {
    val picasso = Picasso.Builder(context)
        .listener { _, _, e ->
            Log.e("Picasso Error", e.stackTraceToString())
        }.build()
    picasso.load(url).placeholder(R.drawable.placeholder).into((view))
}