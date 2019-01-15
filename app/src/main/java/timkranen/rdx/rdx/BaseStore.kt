package timkranen.rdx.rdx

abstract class BaseStore<State>(initialState: State,
                                private val reducers: List<Reducer<State>> = listOf(),
                                private val middleware: List<Middleware<State>> = listOf()) : Store<State> {

    protected var currentState: State = initialState

    override fun getState(state: State): State = currentState

    protected fun dispatch(action: Action) {
        val newAction = applyMiddleware(currentState, action)
        val newState = applyReducers(currentState,
                                     newAction)

        if (newState == currentState) {
            return
        }

        currentState = newState

        publish()
    }

    protected abstract fun publish()

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
}
