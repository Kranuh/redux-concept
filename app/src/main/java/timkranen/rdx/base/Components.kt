package timkranen.rdx.base

/**
 * Represents the Action the be executed
 */
interface Action

typealias Next<State> = (State, Action, Dispatcher) -> Action

/**
 * Middleware
 */
typealias Middleware<State> = (State, Action, Dispatcher, Next<State>) -> Action


typealias Reducer<State> = (State, Action) -> State

/**
 * Dispatches actions to the store
 */
typealias Dispatcher = (Action) -> Unit

/**
 * Represents a listener to be triggered when State changes
 */
typealias Subscription<State> = (State, Dispatcher) -> Unit

/**
 * The main store of the application
 */
interface Store<State> {
    fun getState(state: State): State
    fun subscribe(subscription: Subscription<State>)
    fun unsubscribe(subscription: Subscription<State>)
}
