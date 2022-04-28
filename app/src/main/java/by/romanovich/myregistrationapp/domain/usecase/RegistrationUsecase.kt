package by.romanovich.myregistrationapp.domain.usecase

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile

interface RegistrationUsecase {
    fun registration(
        login: String,
        email: String,
        password: String,
        callback: CallbackMain<UserProfile>
    )
}