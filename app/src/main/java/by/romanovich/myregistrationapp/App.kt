package by.romanovich.myregistrationapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import by.romanovich.myregistrationapp.data.LoginUsecaseImpl
import by.romanovich.myregistrationapp.data.dataBase.AccountsDAO
import by.romanovich.myregistrationapp.data.dataBase.AccountsDB
import by.romanovich.myregistrationapp.data.loginApiImpl.MockLoginApiImpl
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.LoginUsecase


//создаём апи на все приложение единожды
class App : Application() {
    //никто кроме активити не знает о существовании loginApi
    private val loginApi: LoginApi by lazy { MockLoginApiImpl(getAccountDao()) }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(
            app.loginApi,
            Handler(Looper.getMainLooper())
        )
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AccountsDB::class.java, "Accounts.db").build()
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