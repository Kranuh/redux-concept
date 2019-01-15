package timkranen.rdx.rdx_rx

import io.reactivex.disposables.Disposable
import timkranen.rdx.rdx.Dispatcher

data class RxStateEvent<State>(val state: State, val dispatcher: Dispatcher)

interface RxPublisher<State> {
    fun observe(onNext: (RxStateEvent<State>) -> Unit): Disposable
}

