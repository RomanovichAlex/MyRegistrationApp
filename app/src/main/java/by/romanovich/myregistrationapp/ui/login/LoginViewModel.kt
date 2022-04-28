package by.romanovich.myregistrationapp.ui.login

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.Publisher
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase
import by.romanovich.myregistrationapp.ui.AppState

class LoginViewModel(
    private val loginUsecase: LoginUsecase
) : LoginContract.ViewModel {

    private val liveData: Publisher<AppState> = Publisher()

    override fun getLiveData(): Publisher<AppState> = liveData


    override fun onLogin(login: String, password: String) {
        liveData.postValue(AppState.Loading)
        loginUsecase.login(login, password, object : CallbackMain<UserProfile> {

            override fun onSuccess(result: UserProfile) {
                liveData.postValue(AppState.Success(result))
            }

            override fun onError(error: Exception) {
                liveData.postValue(AppState.Error(error))
            }
        })
    }
}