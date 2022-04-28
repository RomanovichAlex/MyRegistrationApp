package by.romanovich.myregistrationapp.data.usecaseImpl

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.RegistrationUsecase

class RegistrationUsecaseImpl(
    private val api: LoginApi
) : RegistrationUsecase {
    override fun registration(
        login: String,
        password: String,
        email: String,
        callback: CallbackMain<UserProfile>
    ) {
        Thread {
            try {
                //берем апи и результат
                val result = api.registration(login, email, password)
                //передаем результат, запускаем в главном потоке
                callback.onSuccess(result)
            } catch (exc: Exception) {
                callback.onError(exc)
            }
        }.start()
    }
}

