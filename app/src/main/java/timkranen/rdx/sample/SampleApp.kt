package timkranen.rdx.sample

import android.app.Application
import timkranen.rdx.sample.model.AppState
import timkranen.rdx.sample.reducers.MainReducerGroup

class SampleApp: Application() {

    companion object {
        val storeInstance by lazy {
            SampleAppStore(listOf(MainReducerGroup().create()), MiddlewareContainer<AppState>().activeMiddleware)
        }
    }

}
