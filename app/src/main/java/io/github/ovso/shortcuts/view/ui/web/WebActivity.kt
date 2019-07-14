package io.github.ovso.shortcuts.view.ui.web

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.github.ovso.shortcuts.R
import io.github.ovso.shortcuts.view.MyAdView
import kotlinx.android.synthetic.main.activity_web.linearlayout_web_container
import kotlinx.android.synthetic.main.activity_web.toolbar

class WebActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_close)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, WebFragment.newInstance(), WebFragment::class.java.simpleName)
        .commitNow()
    }

    setupAds()
  }

  private fun setupAds() {
    linearlayout_web_container.addView(MyAdView.getAdmobBannerView(this))
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }

  override fun onBackPressed() {
    (supportFragmentManager.findFragmentByTag(WebFragment::class.java.simpleName) as? OnBackPressedListener)?.onBackPressed()
  }
}