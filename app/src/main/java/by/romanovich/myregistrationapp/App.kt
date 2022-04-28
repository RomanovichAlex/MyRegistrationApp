package by.romanovich.myregistrationapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import by.romanovich.myregistrationapp.data.LoginUsecaseImpl
import by.romanovich.myregistrationapp.data.dataBase.AccountsDAO
import by.romanovich.myregistrationapp.data.dataBase.AccountsDB
import by.romanovich.myregistrationapp.data.loginApiImpl.LoginApiImpl
import by.romanovich.myregistrationapp.data.usecaseImpl.PasswordRecoveryUsecaseImpl
import by.romanovich.myregistrationapp.data.usecaseImpl.RegistrationUsecaseImpl
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.usecase.LoginUsecase
import by.romanovich.myregistrationapp.domain.usecase.PasswordRecoveryUsecase
import by.romanovich.myregistrationapp.domain.usecase.RegistrationUsecase


//создаём апи на все приложение единожды
class App : Application() {
    private val loginApi: LoginApi by lazy { LoginApiImpl(getAccountDao()) }

    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(
            app.loginApi
        )
    }
    val passwordRecoveryUsecase: PasswordRecoveryUsecase by lazy {
        PasswordRecoveryUsecaseImpl(app.loginApi)
    }

    val registrationUsecase: RegistrationUsecase by lazy {
        RegistrationUsecaseImpl(app.loginApi)
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AccountsDB::class.java, "Accounts.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private var db: AccountsDB? = null

        fun getAccountDao(): AccountsDAO {
            return db !!.accountDao()
        }
    }
}

//нужен чтобы получить LoginUsecase
val Context.app: App
    get() {
        return applicationContext as App
    }