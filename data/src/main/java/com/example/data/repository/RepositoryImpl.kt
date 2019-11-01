package com.example.data.repository

import android.content.Context
import com.bumptech.glide.Glide
import com.example.data.BuildConfig
import com.example.domain.repository.Repository

class RepositoryImpl(private val context: Context): Repository {
  override suspend fun loadImages() {
    Glide.with(context)
      .load(BuildConfig.X_URL)
      .preload()
    Glide.with(context)
      .load(BuildConfig.O_URL)
      .preload()
  }
}