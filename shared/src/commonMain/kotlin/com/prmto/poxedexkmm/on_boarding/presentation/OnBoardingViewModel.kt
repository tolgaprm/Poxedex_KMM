package com.prmto.poxedexkmm.on_boarding.presentation

import com.prmto.poxedexkmm.splash.domain.usecase.CoreUseCases
import com.rickclephas.kmm.viewmodel.KMMViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class OnBoardingViewModel : KMMViewModel(), KoinComponent {
    private val coreUseCases: CoreUseCases by inject()

    fun onBoardingCompleted() {
        coreUseCases.saveOnBoardingStateUseCase(
            state = true
        )
    }
}