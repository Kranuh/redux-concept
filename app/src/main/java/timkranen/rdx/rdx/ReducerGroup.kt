package timkranen.rdx.rdx

interface ReducerGroup<State> {

    fun create(): Reducer<State> = combineReducers(createReducers())

    private fun combineReducers(reducers: List<Reducer<State>>): Reducer<State> {
        return { state: State, action: Action ->
            var newState = state
            for (r in reducers) { // Figure out why .forEach {} can't return typealias
                newState = r.invoke(newState, action)
            }
            newState
        }
    }

    fun createReducers(): List<Reducer<State>>
}
