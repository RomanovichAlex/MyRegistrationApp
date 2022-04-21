package by.romanovich.myregistrationapp.domain

import androidx.annotation.WorkerThread

//предметная облость, достает данные
interface LoginApi {
    //рабочий поток
    @WorkerThread
    //функция принимает login и password и возращает Boolean
    fun login(login: String, password: String): Boolean

    @WorkerThread
    //функция передает login,email и password и возращает Boolean
    fun register(login: String, password: String, email: String): Boolean

    @WorkerThread
    //функция сервер в курсе что клиент ушел
    fun logout(): Boolean

    @WorkerThread
    //функция забыли пароль
    fun forgotPassword(login: String): Boolean
}