package by.romanovich.myregistrationapp.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey
    val id: String,
    val login: String,
    val password: String,
    val email: String,
    //ссылка на аватарку
    val avatarUrl: String
)