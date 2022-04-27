package by.romanovich.myregistrationapp.data.dataBase

import androidx.room.*


@Dao
interface AccountsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registration(entity: Account)

    @Update
    fun updateAccount(user: Account)

    @Query("SELECT * FROM Account")
    fun getAllAccount(): List<Account>
}