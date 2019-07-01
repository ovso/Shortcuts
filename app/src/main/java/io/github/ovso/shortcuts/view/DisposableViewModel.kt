package io.github.ovso.shortcuts.view

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DisposableViewModel : ViewModel() {
  private val compositeDisposable = CompositeDisposable()
  fun addDisposable(d: Disposable) {
    compositeDisposable.addAll(d)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}