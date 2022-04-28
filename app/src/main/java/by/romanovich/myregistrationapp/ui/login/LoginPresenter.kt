package by.romanovich.myregistrationapp.ui.login

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase
import by.romanovich.myregistrationapp.domain.entities.UserProfile

class LoginPresenter(
    private val loginUsecase: LoginUsecase
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private var isSuccess: Boolean = false


    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setRegister()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        loginUsecase.login(login, password, object : CallbackMain<UserProfile> {

            override fun onSuccess(result: UserProfile) {
                view?.hideProgress()
                view?.loadAccountData(result)
                view?.setSuccess()
                isSuccess = true
            }

            override fun onError(error: Exception) {

                view?.hideProgress()
                view?.setError("Неверный логин или пароль!!!")
                isSuccess = false
            }
        })
    }

    override fun onCredentialsChanged() {
        // todo
    }
}