package by.romanovich.myregistrationapp.ui.forgotPassword

import by.romanovich.myregistrationapp.domain.entities.UserProfile

class PasswordRecoveryContract {
    interface PasswordRecoveryViewInterface {
        fun showProgress()
        fun hideProgress()
        fun setSuccess()
        fun showError(error: Exception)
        fun forgetPasswordData(account: UserProfile)
    }

    interface PasswordRecoveryPresenterInterface  {
        fun onAttachView(view: PasswordRecoveryViewInterface)
        fun findAccount(email: String)
        fun onDetach()
    }
}