package by.romanovich.myregistrationapp.domain.entities
//model
//когда в одном приложении может быть много пользователей

data class UserProfile(
    val id: Int?,
    val login: String,
    val email: String
)