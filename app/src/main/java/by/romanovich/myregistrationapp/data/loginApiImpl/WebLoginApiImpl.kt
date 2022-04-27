package by.romanovich.myregistrationapp.data.loginApiImpl

import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.entities.UserProfile

class WebLoginApiImpl : LoginApi {

    override fun login(login: String, password: String): UserProfile {

        TODO("Not yet implemented")
    }

    override fun register(login: String, email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}