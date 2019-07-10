package io.github.ovso.shortcuts.view.ui.main

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.uber.autodispose.autoDisposable
import io.github.ovso.shortcuts.view.DisposableViewModel
import io.github.ovso.shortcuts.view.adapter.App
import io.reactivex.Observable

class MainViewModel(private val args: MainViewModelArguments) : DisposableViewModel() {
  val appsLiveData = MutableLiveData<MutableList<App>>()
  fun fetchList() {
    args.schdulers?.let {
      Observable.fromCallable {
        // assets to json
        args.resProvider?.toJsonStr("apps.json")
      }.map {
        // jsonStr to JsonArray
        Gson()
          .fromJson(it, JsonElement::class.java)
          .asJsonObject["apps"]
          .asJsonArray
      }.map {
        // jsonArray to items
        val array = Gson().fromJson(it, Array<App>::class.java)
        array.toMutableList()
      }.map {
        // items sort
        it.sortWith(
          Comparator { o1, o2 ->
            o1!!.name.compareTo(o2!!.name, true)
          }
        )
        appsLiveData.postValue(it)
      }
        .subscribeOn(args.schdulers.io())
        .autoDisposable(args.scopeProvider!!)
        .subscribe()
    }
  }
}