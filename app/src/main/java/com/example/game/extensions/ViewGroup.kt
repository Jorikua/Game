package com.example.game.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.forEach
import androidx.core.view.isEmpty

fun ViewGroup.inflate(@LayoutRes res: Int): View {
  return LayoutInflater.from(context).inflate(res, this, false)
}

fun ViewGroup.all(predicate: (View) -> Boolean): Boolean {
  if (isEmpty()) return true
  forEach { if (!predicate(it)) return false }
  return true
}