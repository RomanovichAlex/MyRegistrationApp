package by.romanovich.myregistrationapp.domain.entities
//model
//когда в одном приложении может быть много пользователей

data class UserProfile(
    val id: String,
    val login: String,
    val email: String,
    //ссылка на аватарку
    val avatarUrl: String
)