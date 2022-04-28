package by.romanovich.myregistrationapp.ui.forgotPassword

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase

class PasswordRecoveryPresenter (
    private val passwordRecoveryUseCase: PasswordRecoveryUsecase
) : PasswordRecoveryContract.PasswordRecoveryPresenterInterface {

    private var isSuccess: Boolean = false
    private var view: PasswordRecoveryContract.PasswordRecoveryViewInterface? = null

    override fun onAttachView(view: PasswordRecoveryContract.PasswordRecoveryViewInterface) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun findAccount(email: String) {
        view?.showProgress()
        passwordRecoveryUseCase.passwordRecovery(email, object : CallbackMain<UserProfile> {
            override fun onSuccess(result: UserProfile) {
                view?.hideProgress()
                view?.forgetPasswordData(result)
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