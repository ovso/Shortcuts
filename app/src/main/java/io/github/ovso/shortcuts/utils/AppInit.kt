
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.google.android.gms.ads.MobileAds
import com.pixplicity.easyprefs.library.Prefs
import io.github.ovso.shortcuts.BuildConfig
import timber.log.Timber

object AppInit {
  fun timber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  fun prefs(context: Context) {
    Prefs.Builder()
      .setContext(context)
      .setMode(ContextWrapper.MODE_PRIVATE)
      .setPrefsName(context.packageName)
      .setUseDefaultSharedPreference(true)
      .build()
  }

  fun ad(context: Context) {
    MobileAds.initialize(context, Ads.ADMOB_APP_ID.value)
  }

  fun crashHandling(app: Application) {
    val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler { _, _ ->
      // Crashlytics에서 기본 handler를 호출하기 때문에 이중으로 호출되는것을 막기위해 빈 handler로 설정
    }
    //Fabric.with(this, Crashlytics())
    val fabricExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler(
      MyExceptionHandler(
        app,
        defaultExceptionHandler,
        fabricExceptionHandler
      )
    )
  }
}