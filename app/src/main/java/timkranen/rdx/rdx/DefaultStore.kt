package timkranen.rdx.rdx

abstract class DefaultStore<State>(
        initialState: State,
        reducers: List<Reducer<State>> = listOf(),
        middleware: List<Middleware<State>> = listOf()
) : BaseStore<State>(initialState,
                     reducers,
                     middleware), DefaultStorePublisher<State> {

    private val subscriptions = arrayListOf<Subscription<State>>()

    override fun publish() {
        subscriptions.forEach { subscription ->
            subscription(
                    currentState,
                    ::dispatch
            )
        }
    }

    override fun subscribe(subscription: Subscription<State>) {
        subscriptions.add(subscription)
        subscription(
                currentState,
                ::dispatch
        )
    }

    override fun unsubscribe(subscription: Subscription<State>) {
        subscriptions.remove(subscription)
    }
}
