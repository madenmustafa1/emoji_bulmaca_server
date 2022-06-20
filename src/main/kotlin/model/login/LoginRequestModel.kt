package model.login

data class LoginRequestModel(
    val username: String,
    val password: String,
    val key: String
)
