package by.romanovich.myregistrationapp.domain

import androidx.annotation.MainThread
import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//iterator
//предметная облость, уже несет данные
//выносим работу с многопоточностью
interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        //функция обратный вызов, после завершения метода, лямпда, замыкание Boolean который возращает Unit(ничего)
        @MainThread callback: CallbackMain<UserProfile>
    )
}