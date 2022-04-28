package by.romanovich.myregistrationapp.ui.registration

import by.romanovich.myregistrationapp.CallbackMain
import by.romanovich.myregistrationapp.Publisher
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.domain.usecase.RegistrationUsecase
import by.romanovich.myregistrationapp.ui.state.AppState

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUsecase
) : RegistrationContract.ViewModel {

    private val liveData: Publisher<AppState> = Publisher()
    override fun getLiveData(): Publisher<AppState> = liveData

    override fun onRegistration(login: String, password: String, email: String) {
        liveData.postValue(AppState.Loading)
        registrationUseCase.registration(
            login,
            password,
            email,
            object : CallbackMain<UserProfile> {
                override fun onSuccess(result: UserProfile) {
                    liveData.postValue(AppState.Success(result))
                }

                override fun onError(error: Exception) {
                    liveData.postValue(AppState.Error(error))
                }
            })
    }
}

