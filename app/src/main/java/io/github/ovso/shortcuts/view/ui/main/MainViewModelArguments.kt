package io.github.ovso.shortcuts.view.ui.main

import io.github.ovso.shortcuts.utils.ResourceProvider
import io.github.ovso.shortcuts.utils.rx.Schedulers

class MainViewModelArguments(
  val resProvider: ResourceProvider?,
  val schdulers: Schedulers?
) {
  data class Builder(
    private var resProvider: ResourceProvider? = null,
    private var schedulers: Schedulers? = null
  ) {

    fun resProvider(resProvider: ResourceProvider) = apply { this.resProvider = resProvider }
    fun schedulers(schedulers: Schedulers) = apply { this.schedulers = schedulers }

    fun build() = MainViewModelArguments(resProvider, schedulers)
  }
}