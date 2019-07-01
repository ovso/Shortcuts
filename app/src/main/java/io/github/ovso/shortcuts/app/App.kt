package io.github.ovso.shortcuts.app

import android.app.Application
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    AppInit.timber()
    AppInit.prefs(this)
    AppInit.ad(this)
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    AppInit.crashHandling(this)
  }
}