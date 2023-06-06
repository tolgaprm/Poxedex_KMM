package com.prmto.poxedexkmm.splash.domain.usecase

import com.prmto.poxedexkmm.splash.domain.repo.KeyValueLocalRepo

class SaveOnBoardingStateUseCase(
    private val keyValueLocalRepo: KeyValueLocalRepo
) {

    operator fun invoke(state: Boolean) {
        keyValueLocalRepo.saveOnboardingState(state)
    }
}