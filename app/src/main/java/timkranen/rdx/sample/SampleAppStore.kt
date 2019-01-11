package timkranen.rdx.sample

import timkranen.rdx.base.BaseStore
import timkranen.rdx.base.Middleware
import timkranen.rdx.base.Reducer
import timkranen.rdx.sample.model.AppState

class SampleAppStore(reducers: List<Reducer<AppState>>, middleware: List<Middleware<AppState>>): BaseStore<AppState>(AppState(), reducers, middleware)
