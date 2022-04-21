package by.romanovich.myregistrationapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import by.romanovich.myregistrationapp.data.LoginUsecaseImpl
import by.romanovich.myregistrationapp.data.loginApiImpl.MockLoginApiImpl
import by.romanovich.myregistrationapp.domain.LoginApi
import by.romanovich.myregistrationapp.domain.LoginUsecase


//создаём апи на все приложение единожды
class App : Application() {
    //никто кроме активити не знает о существовании loginApi
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(
            app.loginApi,
            Handler(Looper.getMainLooper())
        )
    }
}

//нужен чтобы получить LoginUsecase
val Context.app: App
    get() {
        return applicationContext as App
    }