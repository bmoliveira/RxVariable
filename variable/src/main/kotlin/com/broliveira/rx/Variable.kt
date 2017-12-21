package com.broliveira.rx

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Helper to create observable boxed variables, emits a new object
 * every time that we assign a new value.
 *
 * Due to RxJava2 null assertion on all subjects we wrap the value into a box
 * that contains the real value of the observable stream.
 *
 */
open class Variable<T: Any>(private var _value: T) {
  open val subject: Subject<T> = BehaviorSubject
      .create<T>()
      .apply { onNext(_value) }

  var value : T
    @Synchronized get() = _value
    @Synchronized set(value) {
      _value = value
      subject.onNext(_value)
    }

  fun hide(): Observable<T> {
    return subject.hide()
  }
}
