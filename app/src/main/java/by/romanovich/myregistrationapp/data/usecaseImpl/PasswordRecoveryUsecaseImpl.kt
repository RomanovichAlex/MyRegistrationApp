package by.romanovich.myregistrationapp.data.usecaseImpl

import android.os.Handler
import androidx.annotation.MainThread
import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase

class PasswordRecoveryUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : PasswordRecoveryUsecase {
    override fun passwordRecovery(
        email: String,
        @MainThread callback: CallbackMain<UserProfile>
    ) {
        Thread {
            try {
                //берем апи и результат
                val result = api.passwordRecovery(email)
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

