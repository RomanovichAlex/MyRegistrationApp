package by.romanovich.myregistrationapp.model

class LoginRequest {
    fun request(login: String, password: String): Boolean {
        return login == password
    }
}