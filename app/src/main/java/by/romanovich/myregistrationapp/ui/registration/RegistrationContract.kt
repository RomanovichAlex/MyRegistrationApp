package by.romanovich.myregistrationapp.ui.registration

import by.romanovich.myregistrationapp.domain.entities.UserProfile

class RegistrationContract {
    interface RegistrationViewInterface {
        fun showProgress()
        fun hideProgress()
        fun setSuccess()
        fun showError(error: Exception)
        fun loadAccountInRoom(account: UserProfile)
    }

    interface RegistrationPresenterInterface  {
        fun onAttachView(view: RegistrationViewInterface)
        fun onRegistration(login: String, email: String, password: String)
        fun onDetach()
    }
}