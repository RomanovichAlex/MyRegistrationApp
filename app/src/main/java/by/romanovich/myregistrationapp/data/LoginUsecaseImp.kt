package by.romanovich.myregistrationapp.data

import android.os.Handler
import androidx.annotation.MainThread
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.LoginUsecase

//промежуточный элемент между апи и презентором
//передаеем в конструктор апи и хэндлер
class LoginUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUsecase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            //берем апи и результат
            val result = api.login(login, password)
            uiHandler.post {
                //передаем результат, запускаем в главном потоке
                callback(result)
            }
        }.start()
    }
}