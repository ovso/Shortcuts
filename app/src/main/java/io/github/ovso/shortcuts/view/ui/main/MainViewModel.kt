package io.github.ovso.shortcuts.view.ui.main

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.github.ovso.shortcuts.view.DisposableViewModel
import io.github.ovso.shortcuts.view.adapter.App
import io.reactivex.Observable

class MainViewModel(private val args: MainViewModelArguments) : DisposableViewModel() {
  val appsLiveData = MutableLiveData<MutableList<App>>()
  fun fetchList() {
    args.schdulers?.let {
      addDisposable(
        Observable.fromCallable {
          val apps = args.resProvider?.toJsonStr("apps.json")
          apps?.let {
            val jsonArray = Gson()
              .fromJson(apps, JsonElement::class.java)
              .asJsonObject["apps"]
              .asJsonArray
            val array = Gson().fromJson(jsonArray, Array<App>::class.java)
            val items = array.toMutableList()
            items.sortWith(
              Comparator { o1, o2 ->
                o1!!.name.compareTo(o2!!.name, true)
              }
            )
            appsLiveData.postValue(items)
          }
        }.subscribeOn(it.io()).subscribe()
      )
    }
  }
}