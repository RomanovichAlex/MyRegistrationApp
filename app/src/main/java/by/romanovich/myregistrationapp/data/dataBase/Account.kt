package by.romanovich.myregistrationapp.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "LOGIN")
    val login: String,
    @ColumnInfo(name = "PASSWORD")
    val password: String,
    @ColumnInfo(name = "EMAIL")
    val email: String
)