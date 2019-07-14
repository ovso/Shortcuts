package io.github.ovso.shortcuts.view.adapter

import android.content.Intent
import android.view.View
import io.github.ovso.shortcuts.R
import io.github.ovso.shortcuts.utils.ResourceProvider
import io.github.ovso.shortcuts.view.ui.web.WebActivity
import io.github.ovso.shortcuts.view.ui.web.WebViewModel.RxBusWeb
import timber.log.Timber

class MainItemViewModel(
  private val resProvider: ResourceProvider,
  private val item: AppInfo?
) {

  val title: String
    get() = item?.name!!

  fun onClick(v: View) {
    Timber.d("url = $url")
    v.context.run {
      io.github.ovso.shortcuts.app.App.rxBus2.send(RxBusWeb(title, url))
      startActivity(Intent(this, WebActivity::class.java))
    }
  }

  private val url: String?
    get() = item?.url?.asJsonObject?.get(resProvider.getString(R.string.language_code))?.asString

}