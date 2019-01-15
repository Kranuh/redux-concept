package timkranen.rdx.sample.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import timkranen.rdx.rdx.Dispatcher
import timkranen.rdx.rdx_rx.RxStateEvent
import timkranen.rdx.sample.SampleAppStore
import timkranen.rdx.sample.model.AppState
import timkranen.rdx.sample.model.LoginUser
import timkranen.rdx.sample.model.LoginViewState

// Use DI
class MainViewModel(store: SampleAppStore): ViewModel() {

    private var dispatcher: Dispatcher? = null

    val viewState = MutableLiveData<LoginViewState>()

    private var stateDisposable: Disposable? = null
    init {
        stateDisposable = store.observe { event ->
            dispatcher = event.dispatcher
            viewState.value = mapStateToViewState(event.state)
        }
    }

    private fun mapStateToViewState(state: AppState) = LoginViewState(state.loginState.isLoading, state.userState.userId, state.userState.userName)

    fun login() {
        dispatcher?.invoke(LoginUser("1"))
    }

    override fun onCleared() {
        super.onCleared()
        stateDisposable?.dispose()
    }

}
