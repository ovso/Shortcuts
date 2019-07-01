package io.github.ovso.shortcuts.utils.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Schedulers {

  fun io() = Schedulers.io()

  fun ui() = AndroidSchedulers.mainThread()

  fun computation() = Schedulers.computation()
}