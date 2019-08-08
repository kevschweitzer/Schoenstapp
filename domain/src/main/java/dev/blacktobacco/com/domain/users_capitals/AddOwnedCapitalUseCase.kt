package dev.blacktobacco.com.domain.users_capitals

class AddOwnedCapitalUseCase(private val repository: UsersCapitalsRepository) {

    fun execute(capitalId: String) {
        repository.addOwnedCapital(capitalId)
    }
}