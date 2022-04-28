package by.romanovich.myregistrationapp.ui.registration

import by.romanovich.myregistrationapp.Publisher
import by.romanovich.myregistrationapp.ui.state.AppState

class RegistrationContract {
    interface ViewModel {
        fun getLiveData(): Publisher<AppState>
        fun onRegistration(login: String, password: String, email: String)
    }
}