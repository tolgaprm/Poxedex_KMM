package com.prmto.poxedexkmm.android.splash.di

import com.prmto.poxedexkmm.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidSplashModule = module {
    viewModel { SplashViewModel() }
}