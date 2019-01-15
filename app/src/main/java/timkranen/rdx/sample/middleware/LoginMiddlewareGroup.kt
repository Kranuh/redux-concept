package timkranen.rdx.sample.middleware

import timkranen.rdx.rdx.*
import timkranen.rdx.sample.model.AppState
import timkranen.rdx.sample.model.LoginInProgress
import timkranen.rdx.sample.model.LoginUser
import timkranen.rdx.sample.network.Api

class LoginMiddlewareGroup: MiddlewareGroup<AppState> {
    override fun getIdentifier() = "login"

    override fun getMiddleware() = listOf(loginMiddleware)

    private val loginMiddleware = { state: AppState, action: Action, dispatcher: Dispatcher, next: Next<AppState> ->
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
