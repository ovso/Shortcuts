package io.github.ovso.shortcuts.view.adapter

import com.google.gson.JsonElement

class MainItemViewModel(private val item: JsonElement?) {

  val title: String
    get() = item!!.asJsonObject["name"].asString
}