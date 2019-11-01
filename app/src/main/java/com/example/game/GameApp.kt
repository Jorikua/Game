package com.example.game

import android.app.Application
import com.example.game.di.interactorsModule
import com.example.game.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GameApp : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      if (BuildConfig.DEBUG) {
        androidLogger(Level.DEBUG)
      }

      androidContext(this@GameApp)
      modules(
        listOf(
          mainModule,
          interactorsModule
        )
      )
    }
  }
}