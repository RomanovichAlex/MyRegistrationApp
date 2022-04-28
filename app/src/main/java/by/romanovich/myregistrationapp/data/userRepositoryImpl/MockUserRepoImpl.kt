package by.romanovich.myregistrationapp.data.userRepositoryImpl

import by.romanovich.myregistrationapp.domain.UserRepo
import by.romanovich.myregistrationapp.domain.entities.UserProfile

class MockUserRepoImpl : UserRepo {
    override fun addUser(user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<UserProfile> {
        return listOf(UserProfile(0, "login", "email"))
    }

    override fun changeUser(id: String, user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}

