package timkranen.rdx.sample

import android.app.Application
import timkranen.rdx.rdx.MiddlewareContainer
import timkranen.rdx.sample.middleware.GlobalMiddlewareGroup
import timkranen.rdx.sample.middleware.LoginMiddlewareGroup
import timkranen.rdx.sample.model.AppState
import timkranen.rdx.sample.reducers.MainReducerGroup

class SampleApp: Application() {

    companion object {
        val storeInstance by lazy {

            val middlewareContainer = MiddlewareContainer<AppState>().apply {
                addGroups(LoginMiddlewareGroup(), GlobalMiddlewareGroup())
            }

            SampleAppStore(listOf(MainReducerGroup().create()), middlewareContainer.allMiddleware)
        }
    }

}
