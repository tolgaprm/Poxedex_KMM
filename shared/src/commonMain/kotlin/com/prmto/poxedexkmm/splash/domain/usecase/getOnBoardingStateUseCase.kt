package com.prmto.poxedexkmm.splash.domain.usecase

import com.prmto.poxedexkmm.splash.domain.repo.KeyValueLocalRepo

class GetOnBoardingStateUseCase(
    private val keyValueLocalRepo: KeyValueLocalRepo
) {
    operator fun invoke(): Boolean {
        return keyValueLocalRepo.getOnboardingState()
    }
}