package com.prmto.poxedexkmm

import android.app.Application
import com.prmto.poxedexkmm.di.initKoin
import com.prmto.poxedexkmm.splash.presentation.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class PoxedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@PoxedexApplication)
            modules(modulasdae)
        }
    }

    val modulasdae = module {
        viewModel { SplashViewModel() }
    }
}