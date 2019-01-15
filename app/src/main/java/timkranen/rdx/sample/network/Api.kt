package timkranen.rdx.sample.network

import kotlinx.coroutines.*
import timkranen.rdx.rdx.Dispatcher
import timkranen.rdx.sample.model.LoginUserCompleted
import timkranen.rdx.sample.model.User

class Api {
    fun login(userId: String, dispatcher: Dispatcher) {
        GlobalScope.launch(context = Dispatchers.Main) {
            val user = GlobalScope.async {
                loginUserWithId(userId)
            }.await()
            dispatcher.invoke(LoginUserCompleted(user.userId, user.userName))
        }
    }

    private suspend fun loginUserWithId(userId: String): User {
        delay(4000L)
        return User(userId, "Some user name")
    }
}
