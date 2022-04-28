package by.romanovich.myregistrationapp.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey
    val id: Int?,
    val login: String,
    val password: String,
    val email: String
)