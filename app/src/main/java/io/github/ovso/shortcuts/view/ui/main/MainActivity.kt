package io.github.ovso.shortcuts.view.ui.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.google.android.material.navigation.NavigationView
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import de.psdev.licensesdialog.LicensesDialog
import io.github.ovso.shortcuts.R
import io.github.ovso.shortcuts.R.id
import io.github.ovso.shortcuts.R.layout
import io.github.ovso.shortcuts.R.string
import io.github.ovso.shortcuts.databinding.ActivityMainBinding
import io.github.ovso.shortcuts.utils.ResourceProvider
import io.github.ovso.shortcuts.utils.rx.Schedulers
import io.github.ovso.shortcuts.view.MyAdView
import io.github.ovso.shortcuts.view.adapter.MainRevAdapter
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.app_bar_main.linearlayout_main_container
import kotlinx.android.synthetic.main.app_bar_main.recyclerview_main
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import timber.log.Timber

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
  private val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }
  private lateinit var viewModel: MainViewModel
  private var adapter = MainRevAdapter()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupDataBinding(savedInstanceState)
    setupToolbar()
    setupDrawer()
    setupRev()
    fetchList()
    setupAds()
  }

  private fun setupAds() {
    linearlayout_main_container.addView(MyAdView.getAdmobBannerView(this))
  }

  private fun setupRev() {
    recyclerview_main.adapter = adapter
    RecyclerViewDivider.with(this)
      .size(2)
      .color(ContextCompat.getColor(this, R.color.colorPrimary))
      .build()
      .addTo(recyclerview_main)
  }

  private fun fetchList() {
    viewModel.appsLiveData.observe(this, Observer {
      Timber.d("size = ${it.size}")
      adapter.items.addAll(it)
      adapter.notifyDataSetChanged()
    })
    viewModel.fetchList()
  }

  private fun setupDrawer() {
    val navView: NavigationView = findViewById(id.nav_view)
    val toggle = ActionBarDrawerToggle(
      this, drawer_layout, toolbar,
      string.navigation_drawer_open,
      string.navigation_drawer_close
    )
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    navView.setNavigationItemSelectedListener(this)
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
  }

  private fun setupDataBinding(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      val contentView = DataBindingUtil.setContentView<ActivityMainBinding>(
        this,
        layout.activity_main
      )
      viewModel = provideViewModel()
      contentView.viewModel = viewModel
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun provideViewModel() = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
      MainViewModel(provideMainArguments()) as T
  }).get(MainViewModel::class.java)

  private fun provideMainArguments(): MainViewModelArguments {
    return MainViewModelArguments.Builder()
      .resProvider(ResourceProvider(applicationContext))
      .lifecycleScopeProvider(scopeProvider)
      .schedulers(Schedulers()).build()
  }

  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      id.nav_opensource -> showLicenseDialog()
      id.nav_share -> navigateToShare()
      id.nav_review -> navigateToReview()
    }
    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }

  private fun showLicenseDialog() {
    LicensesDialog.Builder(this)
      .setNotices(R.raw.notices)
      .build()
      .show()
  }

  private fun navigateToShare() {
    val intent = Intent(Intent.ACTION_SEND).apply {
      addCategory(Intent.CATEGORY_DEFAULT)
      putExtra(Intent.EXTRA_TITLE, "Share")
      type = "text/plain"
      putExtra(Intent.EXTRA_TEXT, "market://details?value=$packageName")
    }
    startActivity(Intent.createChooser(intent, "App share"))
  }

  private fun navigateToReview() {
    val intent = Intent(Intent.ACTION_VIEW).apply {
      val uriString = "https://play.google.com/store/apps/details?id=$packageName"
      data = Uri.parse(uriString)
      setPackage("com.android.vending")
    }
    try {
      startActivity(intent)
    } catch (e: ActivityNotFoundException) {
      Timber.e(e)
    }
  }
}
