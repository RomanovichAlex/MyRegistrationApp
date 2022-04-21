package by.romanovich.myregistrationapp.ui.login

import by.romanovich.myregistrationapp.domain.LoginUsecase

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
        //в котлине передаем лямбдами result из LoginUsecaseImpl
        loginUsecase.login(login, password) { result ->
            view?.hideProgress()
            isSuccess = if (result) {
                view?.setSuccess()
                true
            } else {
                view?.setError("Неверный логин или пароль!!!")
                false
            }
        }
    }

    override fun onCredentialsChanged() {
        // todo
    }
}