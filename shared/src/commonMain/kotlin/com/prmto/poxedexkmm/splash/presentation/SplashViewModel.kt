package com.prmto.poxedexkmm.splash.presentation

import com.prmto.poxedexkmm.splash.domain.usecase.CoreUseCases
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class SplashViewModel : KMMViewModel(), KoinComponent {

    private val coreUseCases: CoreUseCases by inject()

    private val _onBoardingCompleted = MutableStateFlow(viewModelScope, false)

    @NativeCoroutinesState
    val onBoardingCompleted = _onBoardingCompleted.asStateFlow()

    init {
        viewModelScope.coroutineScope.launch {
            _onBoardingCompleted.value = coreUseCases.getOnBoardingStateUseCase()
        }
    }
}