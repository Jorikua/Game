package com.example.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.PreloadImagesInteractor
import kotlinx.coroutines.launch

class MainViewModel(private val preloadImagesInteractor: PreloadImagesInteractor) : ViewModel() {
  fun preloadImages() {
    viewModelScope.launch {
      preloadImagesInteractor.invoke()
    }
  }
}