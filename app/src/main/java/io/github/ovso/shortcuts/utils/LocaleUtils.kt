package io.github.ovso.shortcuts.utils

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import java.util.Locale

object LocaleUtils {
  val language: String
    get() = locale.language
  val country: String
    get() = locale.country
  private val locale: Locale
    get() = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)
}