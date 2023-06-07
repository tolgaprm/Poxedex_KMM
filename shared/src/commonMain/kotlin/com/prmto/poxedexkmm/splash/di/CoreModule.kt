package com.prmto.poxedexkmm.splash.di

import com.prmto.poxedexkmm.on_boarding.presentation.OnBoardingData
import com.prmto.poxedexkmm.on_boarding.presentation.getOnBoardingData
import com.prmto.poxedexkmm.splash.data.SettingKeyValueImpl
import com.prmto.poxedexkmm.splash.domain.repo.KeyValueLocalRepo
import com.prmto.poxedexkmm.splash.domain.usecase.CoreUseCases
import com.prmto.poxedexkmm.splash.domain.usecase.GetOnBoardingStateUseCase
import com.prmto.poxedexkmm.splash.domain.usecase.SaveOnBoardingStateUseCase
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val splashModule = module {
    single { Settings() }

    single<KeyValueLocalRepo> { SettingKeyValueImpl(get()) }

    single {
        CoreUseCases(
            getOnBoardingStateUseCase = GetOnBoardingStateUseCase(get()),
            saveOnBoardingStateUseCase = SaveOnBoardingStateUseCase(get())
        )
    }

    single<List<OnBoardingData>> {
        getOnBoardingData()
    }
}