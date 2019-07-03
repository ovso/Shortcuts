package io.github.ovso.shortcuts.view.adapter

import com.google.gson.JsonElement

data class App(
  var name: String, val url: JsonElement
//  var url: Url
)

data class Url(
  var ar: String,
  var en: String,
  var es: String,
  var hi: String,
  var ko: String,
  var zh: String
)