package by.romanovich.myregistrationapp.ui.forgotPassword

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.Publisher
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase
import by.romanovich.myregistrationapp.ui.AppState

class PasswordRecoveryViewModel(
    private val passwordRecoveryUseCase: PasswordRecoveryUsecase
) : PasswordRecoveryContract.ViewModel {
    private val liveData: Publisher<AppState> = Publisher()

    override fun getLiveData(): Publisher<AppState> = liveData


    override fun findAccount(email: String) {
        liveData.postValue(AppState.Loading)
        passwordRecoveryUseCase.passwordRecovery(email, object : CallbackMain<UserProfile> {
            override fun onSuccess(result: UserProfile) {
                liveData.postValue(AppState.Success(result))
            }

            override fun onError(error: Exception) {
                liveData.postValue(AppState.Error(error))
            }
        })
    }
}