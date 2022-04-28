package by.romanovich.myregistrationapp.data

import android.os.Handler
import androidx.annotation.MainThread
import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//промежуточный элемент между апи и презентором
//передаеем в конструктор апи и хэндлер
class LoginUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUsecase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: CallbackMain<UserProfile>
    ) {
        Thread {
            try {
                //берем апи и результат
                val result = api.login(login, password)
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
