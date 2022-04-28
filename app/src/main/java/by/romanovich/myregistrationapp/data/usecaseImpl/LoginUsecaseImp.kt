package by.romanovich.myregistrationapp.data

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase

//промежуточный элемент между апи и презентором
//передаеем в конструктор апи и хэндлер
class LoginUsecaseImpl(
    private val api: LoginApi
) : LoginUsecase {
    override fun login(
        login: String,
        password: String,
        callback: CallbackMain<UserProfile>
    ) {
        Thread {
            try {
                //берем апи и результат
                val result = api.login(login, password)
                //передаем результат, запускаем в главном потоке
                callback.onSuccess(result)
            } catch (exc: Exception) {
                callback.onError(exc)
            }
        }.start()
    }
}
