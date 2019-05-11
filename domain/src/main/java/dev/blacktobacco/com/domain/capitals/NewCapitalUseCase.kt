package dev.blacktobacco.com.domain.capitals

class NewCapitalUseCase(val service: CapitalsService) {

    fun execute(capital: Capital){
        service.newCapital()
    }
}