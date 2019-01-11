package timkranen.rdx.sample.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import timkranen.rdx.base.Dispatcher
import timkranen.rdx.sample.SampleAppStore
import timkranen.rdx.sample.model.AppState
import timkranen.rdx.sample.model.LoginUser
import timkranen.rdx.sample.model.LoginViewState

// Use DI
class MainViewModel(private val store: SampleAppStore): ViewModel() {

    private var dispatcher: Dispatcher? = null

    val viewState = MutableLiveData<LoginViewState>()

    init {
        store.subscribe { state: AppState, dispatcher: Dispatcher ->
            this.dispatcher = dispatcher
            viewState.value = mapStateToViewState(state)
        }
    }

    private fun mapStateToViewState(state: AppState) = LoginViewState(state.loginState.isLoading, state.userState.userId, state.userState.userName)

    fun login() {
        dispatcher?.invoke(LoginUser("1"))
    }

}
