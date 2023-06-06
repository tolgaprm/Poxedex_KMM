package com.prmto.poxedexkmm.android.on_boarding.di

import com.prmto.poxedexkmm.on_boarding.presentation.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidOnBoardingModule = module {
    viewModel { OnBoardingViewModel() }
}