package dev.blacktobacco.com.domain.capitals

class GetCapitalsUseCase(private val repository: CapitalsRepository) {

    fun execute() = repository.getCapitals()
}