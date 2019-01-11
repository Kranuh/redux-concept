package timkranen.rdx.sample

import android.util.Log
import timkranen.rdx.base.Action
import timkranen.rdx.base.Dispatcher
import timkranen.rdx.base.Middleware
import timkranen.rdx.base.Next
import timkranen.rdx.sample.model.LoginInProgress
import timkranen.rdx.sample.model.LoginUser
import timkranen.rdx.sample.network.Api

class MiddlewareContainer<State> {

    val activeMiddleware: List<Middleware<State>>
        get() {
            return listOf(loggingMiddleware, loginMiddleware)
        }

    private val loggingMiddleware = { state: State, action: Action, dispatcher: Dispatcher, next: Next<State> ->
        // log every action
        Log.d("LoggingMiddleware", action.toString())
        next.invoke(state, action, dispatcher)
    }

    private val loginMiddleware = { state: State, action: Action, dispatcher: Dispatcher, next: Next<State> ->
        when(action) {
            is LoginUser -> {
                Api().login(action.userId, dispatcher)
                dispatcher.invoke(LoginInProgress)
                next.invoke(state, action, dispatcher)
            }
            else -> next.invoke(state, action, dispatcher)
        }
    }
}
