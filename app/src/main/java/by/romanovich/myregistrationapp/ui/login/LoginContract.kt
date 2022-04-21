package by.romanovich.myregistrationapp.ui.login

import androidx.annotation.MainThread

//model
//контракт для всех сущностей
class LoginContract {

    //все методы view
    interface View {
        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()

        @MainThread
        fun setRegister()
        //fun getHandler(): Handler
    }

    //все методы пресентера
    interface Presenter {
        @MainThread
        fun onAttach(view: View)

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onCredentialsChanged()
    }
}