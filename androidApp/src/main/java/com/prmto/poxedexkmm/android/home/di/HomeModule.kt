package com.prmto.poxedexkmm.android.home.di

import com.prmto.poxedexkmm.android.home.presentation.AndroidHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { AndroidHomeViewModel() }
}