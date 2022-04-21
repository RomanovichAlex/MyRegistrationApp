package by.romanovich.myregistrationapp.domain.entities

//когда в одном приложении может быть много пользователей
//например возращает логин
data class UserProfile(
    val id: String,
    val login: String,
    val email: String,
    //ссылка на аватарку
    val avatarUrl: String,
    //пинкод
    val pin: Int
)