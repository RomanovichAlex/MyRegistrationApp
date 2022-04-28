package by.romanovich.myregistrationapp.ui

import by.romanovich.myregistrationapp.domain.entities.UserProfile


sealed class AppState {
    data class Success(val userProfile: UserProfile) : AppState()
    data class Error(val error: Exception) : AppState()
    object Loading : AppState()
}