package by.romanovich.myregistrationapp.presenter

class LoginContract {

    //все методы view
    interface RepositoryView {
        fun setRegister()
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    //все методы пресентера
    interface RepositoryPresenter {
        fun onAttach(view: RepositoryView)
        fun onLogin(login: String, password: String)
    }

}