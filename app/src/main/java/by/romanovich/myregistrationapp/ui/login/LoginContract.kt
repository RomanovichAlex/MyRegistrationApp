package by.romanovich.myregistrationapp.ui.login

import by.romanovich.myregistrationapp.Publisher
import by.romanovich.myregistrationapp.ui.AppState

//model
//контракт для всех сущностей
class LoginContract {

    interface ViewModel {
        fun getLiveData(): Publisher<AppState>
        fun onLogin(login: String, password: String)
    }
}