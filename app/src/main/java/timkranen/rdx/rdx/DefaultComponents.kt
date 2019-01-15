package timkranen.rdx.rdx

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
 * MiddlwareGroup, the String in the pair identifies the group
 */
interface MiddlewareGroup<State> {
    fun getIdentifier(): String
    fun getMiddleware(): List<Middleware<State>>
}


interface DefaultStorePublisher<State> {
    fun subscribe(subscription: Subscription<State>)
    fun unsubscribe(subscription: Subscription<State>)
}

/**
 * The main store of the application
 */
interface Store<State> {
    fun getState(state: State): State
}


