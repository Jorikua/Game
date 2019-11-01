package com.example.game.di

import com.example.domain.interactor.PreloadImagesInteractor
import org.koin.dsl.module

val interactorsModule = module {
  factory { PreloadImagesInteractor(get()) }
}