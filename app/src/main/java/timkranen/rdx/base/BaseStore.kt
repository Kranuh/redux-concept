package timkranen.rdx.base

abstract class BaseStore<State>(initialState: State,
                                private val reducers: List<Reducer<State>> = listOf(),
                                private val middleware: List<Middleware<State>> = listOf()) : Store<State> {

    private var currentState: State = initialState
    private val subscriptions = arrayListOf<Subscription<State>>()

    override fun getState(state: State): State = currentState

    private fun dispatch(action: Action) {
        val newAction = applyMiddleware(currentState, action)
        val newState = applyReducers(currentState,
                                     newAction)

        if (newState == currentState) {
            return
        }

        currentState = newState
        subscriptions.forEach { subscription ->
            subscription(currentState,
                         ::dispatch)
        }
    }

    private fun applyMiddleware(state: State,
                                action: Action): Action {
        return nextMiddleware(0)(state, action, ::dispatch)
    }

    private fun nextMiddleware(index: Int): Next<State> {
        if (index == middleware.size) {
            // last link
            return { _, action, _ -> action }
        }

        return { state, action, dispatcher ->
            middleware[index].invoke(state,
                                     action,
                                     dispatcher,
                                     nextMiddleware(index + 1))
        }
    }

    private fun applyReducers(current: State,
                              action: Action): State {
        var newState = current
        reducers.forEach { reducer ->
            newState = reducer(newState,
                               action)
        }
        return newState
    }

    override fun subscribe(subscription: Subscription<State>) {
        subscriptions.add(subscription)
        subscription(currentState,
                     ::dispatch)
    }

    override fun unsubscribe(subscription: Subscription<State>) {
        subscriptions.remove(subscription)
    }
}
