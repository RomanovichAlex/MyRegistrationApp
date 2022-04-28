package by.romanovich.myregistrationapp.domain

import androidx.annotation.WorkerThread
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//предметная облость, достает данные
interface LoginApi {
    //рабочий поток
    @WorkerThread
    //функция принимает login и password и возращает UserProfile
    fun login(login: String, password: String): UserProfile

    @WorkerThread
    //функция передает login,email и password и возращает UserProfile
    fun registration(login: String, email: String, password: String): UserProfile

    @WorkerThread
    //функция забыли пароль
    fun passwordRecovery(email: String): UserProfile


}