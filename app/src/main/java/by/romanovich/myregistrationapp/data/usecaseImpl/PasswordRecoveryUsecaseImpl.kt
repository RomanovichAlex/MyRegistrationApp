package by.romanovich.myregistrationapp.data.usecaseImpl

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase

class PasswordRecoveryUsecaseImpl(
    private val api: LoginApi
) : PasswordRecoveryUsecase {
    override fun passwordRecovery(
        email: String,
        callback: CallbackMain<UserProfile>
    ) {
        Thread {
            try {
                //берем апи и результат
                val result = api.passwordRecovery(email)
                //передаем результат, запускаем в главном потоке
                callback.onSuccess(result)

            } catch (exc: Exception) {
                callback.onError(exc)
            }
        }.start()
    }
}

