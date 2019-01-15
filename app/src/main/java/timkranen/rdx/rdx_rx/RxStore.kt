package timkranen.rdx.rdx_rx

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import timkranen.rdx.rdx.BaseStore
import timkranen.rdx.rdx.Middleware
import timkranen.rdx.rdx.Reducer

abstract class RxStore<State>(
        initialState: State,
        reducers: List<Reducer<State>> = listOf(),
        middleware: List<Middleware<State>> = listOf()
) : BaseStore<State>(initialState,
                     reducers,
                     middleware), RxPublisher<State> {

    private val publisher: BehaviorSubject<RxStateEvent<State>> = BehaviorSubject.create()

    override fun publish() {
        publisher.onNext(RxStateEvent(currentState, ::dispatch))
    }

    override fun observe(onNext: (RxStateEvent<State>) -> Unit): Disposable {
        publish()
        return publisher.subscribe(onNext)
    }
}
