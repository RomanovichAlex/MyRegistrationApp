package by.romanovich.myregistrationapp.data.loginApiImpl

import by.romanovich.myregistrationapp.domain.LoginApi

//тестовый
class MockLoginApiImpl : LoginApi {

    override fun login(login: String, password: String): Boolean {
        //будет валить приложение если не из главного потока вызвана
        Thread.sleep(3_000)
        return login == password
    }

    override fun register(login: String, email: String,  password: String): Boolean {
        Thread.sleep(2_000)
        //только логин т.к. на одной почте может быть несколько аккаунтов
        return login.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(2_000)
        return true
    }

    override fun forgotPassword(login: String): Boolean {
        Thread.sleep(1_000)
        return false
    }
}