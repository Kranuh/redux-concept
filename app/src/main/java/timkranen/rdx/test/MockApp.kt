//package timkranen.rdx.test
//
//import timkranen.rdx.base.*
//
//// state definition
//data class AppState(val dataState: DataState = DataState())
//
//data class FakeData(val someString: String)
//data class DataState(val loading: Boolean = true,
//                     val request: String = "",
//                     val result: FakeData? = null)
//
//// action definition
//data class LoadData(val request: String) : Action
//
//data class DataLoadingInProgress(val request: String) : Action
//data class DataLoadingCompleted(val request: String,
//                                val result: FakeData) : Action
//
//interface FakeApp {
//    fun startLoad()
//    fun saveState(state: AppState)
//    fun getStateLog(): List<AppState>
//}
//
//class FakeStore : BaseStore<AppState>(AppState()) {
//    companion object {
//        val instance by lazy {
//            FakeStore()
//        }
//    }
//}
//
//class MockApp : FakeApp {
//
//    private val store = FakeStore()
//
//    private var dispatcher: Dispatcher? = null
//
//    private val stateLog = mutableListOf<AppState>()
//
//    init {
//        store.addReducer(::reduceDataState)
//
//        store.subscribe { state, dispatcher ->
//            this.dispatcher = dispatcher
//            saveState(state)
//
//            // continue for progression
//            with(state.dataState) {
//                if(loading) {
//                    notifyComplete()
//                }
//            }
//        }
//    }
//
//    override fun startLoad() {
//        dispatcher?.invoke(LoadData("the_request"))
//    }
//
//    private fun notifyComplete() {
//        dispatcher?.invoke(DataLoadingCompleted("the_request", FakeData("the_result")))
//    }
//
//    override fun saveState(state: AppState) {
//        stateLog.add(state)
//    }
//
//    override fun getStateLog() = stateLog
//
//    private fun reduceDataState(state: AppState,
//                                action: Action): AppState {
//        return when (action) {
//            is LoadData -> state.copy(dataState = DataState(true,
//                                                            action.request,
//                                                            null))
//            is DataLoadingInProgress -> state.copy(dataState = DataState(true,
//                                                                         action.request,
//                                                                         null))
//            is DataLoadingCompleted -> state.copy(dataState = DataState(false,
//                                                                        action.request,
//                                                                        action.result))
//            else -> state
//        }
//    }
//}
//
//class TestReducer<State>: Reducer<State> {
//    override fun invoke(p1: State,
//                        p2: Action): State {
//
//    }
//
//}
