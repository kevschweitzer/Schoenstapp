package dev.blacktobacco.com.domain.capitals

class AddCapitalsUseCase(private val repository: CapitalsRepository) {

    fun execute(id: String, capitalsAmount: Int) = repository.addCapitals(id, capitalsAmount)

}