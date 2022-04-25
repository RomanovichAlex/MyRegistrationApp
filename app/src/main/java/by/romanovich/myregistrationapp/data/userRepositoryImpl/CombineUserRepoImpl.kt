package by.romanovich.myregistrationapp.data.userRepositoryImpl

import by.romanovich.myregistrationapp.domain.UserRepo
import by.romanovich.myregistrationapp.domain.entities.UserProfile

//принимает два параметра
class CombineUserRepoImpl(
    //локальный репозиторий
    private val localRepo: UserRepo,
    //удаленный репозиторий
    private val remoteRepo: UserRepo
) : UserRepo {
    override fun addUser(user: UserProfile) {
        localRepo.addUser(user)
        remoteRepo.addUser(user)
    }

    override fun getAllUsers(): List<UserProfile> {
        TODO("Not yet implemented")
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