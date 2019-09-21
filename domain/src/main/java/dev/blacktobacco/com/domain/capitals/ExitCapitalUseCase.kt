package dev.blacktobacco.com.domain.capitals

class ExitCapitalUseCase(val repository: CapitalsRepository) {

    fun execute(id: String) = repository.exitCapital(id)

}