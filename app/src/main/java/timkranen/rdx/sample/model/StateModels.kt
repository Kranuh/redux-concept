package timkranen.rdx.sample.model

data class AppState(val loginState: LoginState = LoginState(), val userState: UserState = UserState())

data class LoginState(val isLoading: Boolean = false)
data class UserState(val isLoggedIn: Boolean = false, val userId: String = "", val userName: String = "")

