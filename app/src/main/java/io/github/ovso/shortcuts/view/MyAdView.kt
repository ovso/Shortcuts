package io.github.ovso.shortcuts.view

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd

object MyAdView {
  fun getAdmobBannerView(context: Context): AdView {
    val adView = AdView(context).apply {
      adSize = AdSize.BANNER
      adUnitId = Ads.ADMOB_BANNER_UNIT_ID.value
      loadAd(AdRequest.Builder().build())
    }
    return adView
  }

  fun getAdmobInterstitialAd(context: Context): InterstitialAd {
    val interstitialAd = InterstitialAd(context)
    interstitialAd.adUnitId = Ads.ADMOB_INTERSTITIAL_ID.value
    val adRequestBuilder = AdRequest.Builder()
    interstitialAd.loadAd(adRequestBuilder.build())
    return interstitialAd
  }
}