package dev.blacktobacco.com.domain.capitals

class JoinCapitalUseCase(private val capitalsRepository: CapitalsRepository) {

    fun execute(id: String) = capitalsRepository.joinCapital(id)

}