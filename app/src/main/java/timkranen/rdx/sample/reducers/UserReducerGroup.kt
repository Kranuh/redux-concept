package timkranen.rdx.sample.reducers

import timkranen.rdx.base.Action
import timkranen.rdx.base.Reducer
import timkranen.rdx.base.ReducerGroup
import timkranen.rdx.sample.model.*

class UserReducerGroup: ReducerGroup<AppState> {

    override fun createReducers(): List<Reducer<AppState>> = listOf(loginReducer, userModificationReducer)

    private val loginReducer = { state: AppState, action: Action ->
        when(action) {
            is LoginUser -> state.copy(loginState = LoginState(true), userState = UserState(false, "", ""))
            is LoginInProgress -> state.copy(loginState = LoginState(true), userState = UserState(false, "", ""))
            is LoginUserCompleted -> state.copy(loginState = LoginState(false), userState = UserState(true, action.userId, action.userName))
            else -> state
        }
    }

    private val userModificationReducer = { state: AppState, action: Action ->
        when(action) {
            is UserChangeUserName -> state.copy(userState = UserState(userId = action.userId, userName = action.userName))
            else -> state
        }
    }
}
