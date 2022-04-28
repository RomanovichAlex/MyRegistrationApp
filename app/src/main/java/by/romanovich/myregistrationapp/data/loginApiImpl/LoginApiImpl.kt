package by.romanovich.myregistrationapp.data.loginApiImpl

import by.romanovich.myregistrationapp.data.dataBase.Account
import by.romanovich.myregistrationapp.data.dataBase.AccountsDAO
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//тестовый
class LoginApiImpl(private val localDataSource: AccountsDAO) : LoginApi {

    private fun getAllAccounts(): List<Account> = localDataSource.getAllAccount()

    private fun checkData(login: String?, password: String?, email: String?) {
        if (login != null && login.isEmpty()) {
            throw Exception()
        }
        if (password != null && password.isEmpty()) {
            throw Exception()
        }
        if (email != null && email.isEmpty()) {
            throw Exception()
        }
    }

    override fun login(login: String, password: String): UserProfile {
        //будет валить приложение если не из главного потока вызвана
        Thread.sleep(2_000)
        checkData(login, password, null)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.login == login && account.password == password) {
                return convertAccountToUserProfile(account)
            }
        }
        throw Exception()
    }

    override fun registration(login: String, email: String, password: String): UserProfile {
        checkData(login, password, null)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.login == login && account.email == email) {
                throw Exception()
            }
        }
        val newAccount = Account(id = null, login = login, password = password, email = email)
        localDataSource.registration(newAccount)
        return convertAccountToUserProfile(newAccount)
    }


    override fun passwordRecovery(email: String): UserProfile {
        checkData(null, null, email)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.email == email) {
                return convertAccountToUserProfile(account)
            }
        }
        throw Exception()
    }

    private fun convertAccountToUserProfile(account: Account): UserProfile {
        return UserProfile(
            id = account.id,
            login = account.login,
            email = account.email
        )
    }
}
