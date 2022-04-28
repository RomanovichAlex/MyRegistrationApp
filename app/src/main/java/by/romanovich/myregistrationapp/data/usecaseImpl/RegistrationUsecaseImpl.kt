package by.romanovich.myregistrationapp.data.usecaseImpl

import android.os.Handler
import androidx.annotation.MainThread
import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase
import by.romanovich.myregistrationapp.domain.usecase.RegistrationUsecase

class RegistrationUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : RegistrationUsecase {
    override fun registration(
        login: String,
        password: String,
        email: String,
        @MainThread callback: CallbackMain<UserProfile>
    ) {
        Thread {
            try {
                //берем апи и результат
                val result = api.registration(login, password, email)
                uiHandler.post {
                    //передаем результат, запускаем в главном потоке
                    callback.onSuccess(result)
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}

