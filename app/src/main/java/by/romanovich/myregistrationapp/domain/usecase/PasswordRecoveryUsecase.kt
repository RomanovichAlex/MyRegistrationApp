package by.romanovich.myregistrationapp.domain.usecase

import androidx.annotation.MainThread
import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile

interface PasswordRecoveryUsecase {
    fun passwordRecovery(
        email: String,
        //функция обратный вызов, после завершения метода, лямпда, замыкание Boolean который возращает Unit(ничего)
        @MainThread callback: CallbackMain<UserProfile>
    )
}