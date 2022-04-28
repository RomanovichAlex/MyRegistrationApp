package by.romanovich.myregistrationapp.ui.registration

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase
import by.romanovich.myregistrationapp.domain.usecase.RegistrationUsecase

class RegistrationPresenter (
    private val registrationUseCase: RegistrationUsecase
) : RegistrationContract.RegistrationPresenterInterface {

    private var isSuccess: Boolean = false
    private var view: RegistrationContract.RegistrationViewInterface? = null

    override fun onAttachView(view: RegistrationContract.RegistrationViewInterface) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun onRegistration(login: String, password: String, email: String) {
        view?.showProgress()
        registrationUseCase.registration(login, password, email, object : CallbackMain<UserProfile> {
            override fun onSuccess(result: UserProfile) {
                view?.hideProgress()
                view?.loadAccountInRoom(result)
                view?.setSuccess()
                isSuccess = true
            }
            override fun onError(error: Exception) {
                view?.hideProgress()
                view?.showError(error)
                isSuccess = false
            }
        })
    }

    override fun onDetach() {
        this.view = null
    }
}