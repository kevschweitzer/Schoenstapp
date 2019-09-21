package dev.blacktobacco.com.domain.capitals

class DeleteCapitalUseCase(private val repository: CapitalsRepository) {

    fun execute(id: String) = repository.deleteCapital(id)

}