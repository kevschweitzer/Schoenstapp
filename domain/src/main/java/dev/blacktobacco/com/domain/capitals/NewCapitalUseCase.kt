package dev.blacktobacco.com.domain.capitals

class NewCapitalUseCase(val repository: CapitalsRepository) {

    fun execute(capital: Capital) = repository.newCapital(capital)
}