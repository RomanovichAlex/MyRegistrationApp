package by.romanovich.myregistrationapp.presenter


import android.os.Handler
import android.os.Looper
import by.romanovich.myregistrationapp.model.LoginRequest


class LoginPresenter : LoginContract.RepositoryPresenter {
    //для запоминания при перевороте
    private var isSuccess: Boolean = false
    lateinit var view: LoginContract.RepositoryView


    override fun onAttach(view: LoginContract.RepositoryView) {
        this.view = view
        //для сохранения изменений при перевороте
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setRegister()
        }
    }

    override fun onLogin(login: String, password: String) {
        view.showProgress()
        Thread {
            Thread.sleep(2000)
            Handler(Looper.getMainLooper()).post {
                view.hideProgress()
                isSuccess = if (LoginRequest().request(login, password)) {
                    view.setSuccess()
                    true
                } else {
                    view.setError("Неверный логин или пароль!!!")
                    false
                }
            }
        }.start()
    }
}