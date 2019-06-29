import io.github.ovso.shortcuts.BuildConfig

enum class Ads(val value: String) {
  ADMOB_APP_ID(
    if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544~3347511713" else ""
  ),
  ADMOB_BANNER_UNIT_ID(
    if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544/6300978111" else ""
  ),
  ADMOB_INTERSTITIAL_ID(
    if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544/1033173712" else ""
  )
}