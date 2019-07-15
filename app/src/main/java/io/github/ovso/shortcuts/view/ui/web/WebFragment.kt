package io.github.ovso.shortcuts.view.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.ads.AdListener
import io.github.ovso.shortcuts.R
import io.github.ovso.shortcuts.databinding.FragmentWebBinding
import io.github.ovso.shortcuts.view.MyAdView
import kotlinx.android.synthetic.main.fragment_web.webview_web
import kotlinx.android.synthetic.main.layout_web_navigation.button_web_nav_back
import kotlinx.android.synthetic.main.layout_web_navigation.button_web_nav_browser
import kotlinx.android.synthetic.main.layout_web_navigation.button_web_nav_forw
import kotlinx.android.synthetic.main.layout_web_navigation.button_web_nav_refresh
import kotlinx.android.synthetic.main.layout_web_navigation.button_web_nav_share

class WebFragment : Fragment(), OnBackPressedListener {
  private val interstitialAd by lazy {
    MyAdView.getAdmobInterstitialAd(requireContext())
  }

  companion object {
    fun newInstance() = WebFragment()
  }

  private lateinit var viewModel: WebViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding: FragmentWebBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false)
    viewModel = ViewModelProviders.of(this).get(WebViewModel::class.java)
    binding.viewModel = viewModel
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupTitle()
    setupWebView()
    setupWebViewNavi()
    initAds();
  }

  private fun initAds() {

    interstitialAd.adListener = object : AdListener() {
      override fun onAdClosed() {
        super.onAdClosed()
        activity?.finish()
      }

      override fun onAdLoaded() {
        super.onAdLoaded()
      }
    }
  }

  private fun setupWebViewNavi() {
    button_web_nav_back.setOnClickListener { webview_web.goBack() }
    button_web_nav_forw.setOnClickListener { webview_web.goForward() }
    button_web_nav_refresh.setOnClickListener { viewModel.urlObField.set(webview_web.url) }
    button_web_nav_share.setOnClickListener { startActivity(viewModel.shareIntent(webview_web.url)) }
    button_web_nav_browser.setOnClickListener { startActivity(viewModel.browserIntent(webview_web.url)) }
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setupWebView() {
    webview_web.settings.run {
      javaScriptEnabled = true
      setAppCacheEnabled(true)
      cacheMode = WebSettings.LOAD_NO_CACHE
    }
  }

  private fun setupTitle() {
    viewModel.titleLiveData.observe(this, Observer {
      activity?.title = it
    })

  }

  override fun onBackPressed() {
    if (webview_web.canGoBack()) {
      webview_web.goBack()
    } else {
      if (interstitialAd.isLoaded) {
        interstitialAd.show()
      } else {
        activity?.finish()
      }
    }
  }
}

