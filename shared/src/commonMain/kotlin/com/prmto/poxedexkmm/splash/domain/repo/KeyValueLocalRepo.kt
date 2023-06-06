package com.prmto.poxedexkmm.splash.domain.repo

interface KeyValueLocalRepo {

    fun getOnboardingState(): Boolean

    fun saveOnboardingState(state: Boolean)
}