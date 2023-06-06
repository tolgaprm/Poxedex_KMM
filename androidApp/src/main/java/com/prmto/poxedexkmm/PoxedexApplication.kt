package com.prmto.poxedexkmm

import android.app.Application
import com.prmto.poxedexkmm.android.on_boarding.di.androidOnBoardingModule
import com.prmto.poxedexkmm.android.splash.di.androidSplashModule
import com.prmto.poxedexkmm.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class PoxedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@PoxedexApplication)
            modules(androidSplashModule, androidOnBoardingModule)
        }
    }

}