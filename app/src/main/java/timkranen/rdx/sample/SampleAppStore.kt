package timkranen.rdx.sample

import timkranen.rdx.rdx.Middleware
import timkranen.rdx.rdx.Reducer
import timkranen.rdx.rdx_rx.RxStore
import timkranen.rdx.sample.model.AppState

class SampleAppStore(reducers: List<Reducer<AppState>>, middleware: List<Middleware<AppState>>): RxStore<AppState>(AppState(), reducers, middleware)
