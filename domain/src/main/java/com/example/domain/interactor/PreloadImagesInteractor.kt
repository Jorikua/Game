package com.example.domain.interactor

import com.example.domain.interactor.base.InteractorWithoutParams
import com.example.domain.repository.Repository

class PreloadImagesInteractor(private val repository: Repository): InteractorWithoutParams<Unit> {
  override suspend fun invoke() {
    repository.loadImages()
  }
}