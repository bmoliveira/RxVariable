package com.broliveira.rx

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Helper to create observable boxed variables, emits a new object
 * every time that we assign a new value.
 *
 * Due to RxJava2 null assertion on all subjects we wrap the value into a box
 * that contains the real value of the observable stream.
 *
 * This variable uses a publish subject to represent value changes
 */
class PublishVariable<T: Any>(private var _value: T) : Variable<T>(_value) {
  override val subject: Subject<T> =
      PublishSubject
          .create<T>()
          .apply { onNext(_value) }
}
