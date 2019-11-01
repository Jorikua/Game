package com.example.game.di

import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.game.MainViewModel
import com.example.game.game.GameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
  viewModel {
    MainViewModel(get())
  }

  viewModel {
    GameViewModel()
  }

  single<Repository> { RepositoryImpl(androidContext()) }
}