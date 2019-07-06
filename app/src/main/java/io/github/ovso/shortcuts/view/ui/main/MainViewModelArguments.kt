package io.github.ovso.shortcuts.view.ui.main

import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.github.ovso.shortcuts.utils.ResourceProvider
import io.github.ovso.shortcuts.utils.rx.Schedulers

class MainViewModelArguments(
  val resProvider: ResourceProvider?,
  val schdulers: Schedulers?,
  val scopeProvider: AndroidLifecycleScopeProvider?
) {
  data class Builder(
    private var resProvider: ResourceProvider? = null,
    private var schedulers: Schedulers? = null,
    private var scopeProvider: AndroidLifecycleScopeProvider? = null
  ) {

    fun resProvider(resProvider: ResourceProvider) = apply { this.resProvider = resProvider }
    fun schedulers(schedulers: Schedulers) = apply { this.schedulers = schedulers }
    fun lifecycleScopeProvider(scopeProvider: AndroidLifecycleScopeProvider) =
      apply { this.scopeProvider = scopeProvider }

    fun build() = MainViewModelArguments(resProvider, schedulers, scopeProvider)
  }
}