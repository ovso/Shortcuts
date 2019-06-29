package io.github.ovso.shortcuts.view.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import io.github.ovso.shortcuts.R
import io.github.ovso.shortcuts.R.id
import io.github.ovso.shortcuts.R.layout
import io.github.ovso.shortcuts.R.string
import io.github.ovso.shortcuts.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.toolbar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupDataBinding(savedInstanceState)
    setupToolbar()
    setupDrawer()
  }

  private fun setupDrawer() {
    val drawerLayout: DrawerLayout = findViewById(id.drawer_layout)
    val navView: NavigationView = findViewById(id.nav_view)
    val toggle = ActionBarDrawerToggle(
      this, drawerLayout, toolbar,
      string.navigation_drawer_open,
      string.navigation_drawer_close
    )
    drawerLayout.addDrawerListener(toggle)
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
      contentView.viewModel = provideViewModel();
    }
  }

  private fun provideViewModel() = ViewModelProviders.of(this).get(MainViewModel::class.java)

  override fun onBackPressed() {
    val drawerLayout: DrawerLayout = findViewById(id.drawer_layout)
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      id.nav_home -> {
        // Handle the camera action
      }
      id.nav_gallery -> {

      }
      id.nav_slideshow -> {

      }
      id.nav_tools -> {

      }
      id.nav_share -> {

      }
      id.nav_send -> {

      }
    }
    val drawerLayout: DrawerLayout = findViewById(id.drawer_layout)
    drawerLayout.closeDrawer(GravityCompat.START)
    return true
  }
}
