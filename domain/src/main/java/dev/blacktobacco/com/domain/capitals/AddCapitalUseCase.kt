package dev.blacktobacco.com.domain.capitals

class AddCapitalUseCase(private val repository: CapitalsRepository) {

    fun execute(id: String) = repository.addCapital(id)

}