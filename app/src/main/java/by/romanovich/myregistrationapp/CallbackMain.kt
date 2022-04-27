package by.romanovich.myregistrationapp

interface CallbackMain<T> {

    fun onSuccess(result: T)

    fun onError(error: Exception)
}