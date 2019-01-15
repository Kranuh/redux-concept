package timkranen.rdx.sample.middleware

import android.util.Log
import timkranen.rdx.rdx.*
import timkranen.rdx.sample.model.AppState

class GlobalMiddlewareGroup: MiddlewareGroup<AppState> {
    override fun getIdentifier() = "global"

    override fun getMiddleware() = listOf(loggingMiddleware)

    private val loggingMiddleware = { state: AppState, action: Action, dispatcher: Dispatcher, next: Next<AppState> ->
        // log every action
        Log.d("LoggingMiddleware", action.toString())
        next.invoke(state, action, dispatcher)
    }
}
