package timkranen.rdx.sample.model

import timkranen.rdx.base.Action

data class LoginUser(val userId: String): Action
object LoginInProgress: Action
data class LoginUserCompleted(val userId: String, val userName: String): Action
data class UserChangeUserName(val userId: String, val userName: String): Action
