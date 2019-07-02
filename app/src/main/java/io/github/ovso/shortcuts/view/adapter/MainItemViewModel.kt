package io.github.ovso.shortcuts.view.adapter

class MainItemViewModel(private val item: App?) {

  val title: String
    get() = item?.name!!
}