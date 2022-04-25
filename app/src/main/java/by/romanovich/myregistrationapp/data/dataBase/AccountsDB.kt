package by.romanovich.myregistrationapp.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1)
abstract class AccountsDB : RoomDatabase() {
    abstract fun accountDao(): AccountsDAO
}