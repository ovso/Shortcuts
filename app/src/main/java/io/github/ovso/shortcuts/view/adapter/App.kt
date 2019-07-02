package io.github.ovso.shortcuts.view.adapter

data class App(
  var name: String,
  var url: Url
)

data class Url(
  var ar: String,
  var en: String,
  var es: String,
  var hi: String,
  var ko: String,
  var zh: String
)