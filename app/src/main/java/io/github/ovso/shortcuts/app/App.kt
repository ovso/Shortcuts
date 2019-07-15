package io.github.ovso.shortcuts.app

import android.app.Application
import io.github.ovso.shortcus.utils.rx.RxBus2
import io.github.ovso.shortcuts.utils.AppInit
import io.github.ovso.shortcuts.utils.rx.RxBus
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

  companion object {
    var rxBus = RxBus()
    val rxBus2 = RxBus2()
  }

  override fun onCreate() {
    super.onCreate()
    AppInit.timber()
    AppInit.prefs(this)
    AppInit.ads(this)
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    AppInit.crashHandling(this)
  }
}