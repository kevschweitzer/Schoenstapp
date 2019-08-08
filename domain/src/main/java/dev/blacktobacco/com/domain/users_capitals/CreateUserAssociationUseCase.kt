package dev.blacktobacco.com.domain.users_capitals

class CreateUserAssociationUseCase(private val repository: UsersCapitalsRepository ) {

    fun execute(userId: String) {
        repository.createAssociationForUser(userId)
    }
}