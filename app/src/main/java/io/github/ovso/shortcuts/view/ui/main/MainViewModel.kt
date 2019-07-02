package io.github.ovso.shortcuts.view.ui.main

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.shortcuts.view.DisposableViewModel
import io.reactivex.Observable

class MainViewModel(private val args: MainViewModelArguments) : DisposableViewModel() {
  val appsLiveData = MutableLiveData<JsonArray>()
  fun fetchList() {
    args.schdulers?.let {
      addDisposable(
        Observable.fromCallable {
          val apps = args.resProvider?.toJsonStr("apps.json")
          apps?.let {
            val items = Gson()
              .fromJson(apps, JsonElement::class.java)
              .asJsonObject["apps"]
              .asJsonArray
            appsLiveData.postValue(items)
          }
        }.subscribeOn(args.schdulers.io()).subscribe()
      )
    }
  }
}