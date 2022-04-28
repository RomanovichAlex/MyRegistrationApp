package by.romanovich.myregistrationapp.domain.usecase

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//iterator
//предметная облость, уже несет данные
interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        callback: CallbackMain<UserProfile>
    )
}