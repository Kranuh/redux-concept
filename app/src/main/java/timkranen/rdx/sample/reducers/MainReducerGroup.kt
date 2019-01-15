package timkranen.rdx.sample.reducers

import timkranen.rdx.rdx.ReducerGroup
import timkranen.rdx.sample.model.AppState

class MainReducerGroup: ReducerGroup<AppState> {
    override fun createReducers() = listOf(UserReducerGroup().create())
}
