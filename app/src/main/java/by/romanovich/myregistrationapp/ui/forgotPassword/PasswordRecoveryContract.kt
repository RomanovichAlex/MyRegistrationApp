package by.romanovich.myregistrationapp.ui.forgotPassword

import by.romanovich.myregistrationapp.Publisher
import by.romanovich.myregistrationapp.ui.state.AppState

class PasswordRecoveryContract {
    interface ViewModel {
        fun getLiveData(): Publisher<AppState>
        fun findAccount(email: String)
    }
}