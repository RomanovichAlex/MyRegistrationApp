package by.romanovich.myregistrationapp.domain.usecase

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile

interface PasswordRecoveryUsecase {
    fun passwordRecovery(
        email: String,
        callback: CallbackMain<UserProfile>
    )
}