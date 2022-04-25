package by.romanovich.myregistrationapp.data.loginApiImpl

import by.romanovich.myregistrationapp.data.dataBase.Account
import by.romanovich.myregistrationapp.data.dataBase.AccountsDAO
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//тестовый
class MockLoginApiImpl(private val localDataSource: AccountsDAO) : LoginApi {

    private fun getAllAccounts(): List<Account> = localDataSource.getAllAccount()

    private fun checkData(login: String?, password: String?) {
        if (login != null && login.isEmpty()) {
            throw Exception()
        }
        if (password != null && password.isEmpty()) {
            throw Exception()
        }
    }

    override fun login(login: String, password: String): UserProfile {
        //будет валить приложение если не из главного потока вызвана
        Thread.sleep(2_000)
        checkData(login, password)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.login == login && account.password == password) {
                return convertAccountToUserProfile(account)
            }
        }
        throw Exception()
    }

    override fun register(login: String, email: String, password: String): Boolean {
        Thread.sleep(2_000)
        //только логин т.к. на одной почте может быть несколько аккаунтов
        return login.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(2_000)
        return true
    }

    override fun forgotPassword(email: String): Boolean {
        Thread.sleep(1_000)
        return false
    }

    fun convertAccountToUserProfile(account: Account): UserProfile {
        return UserProfile(
            id = account.id,
            login = account.login,
            email = account.email,
            avatarUrl = account.avatarUrl
        )
    }
}
