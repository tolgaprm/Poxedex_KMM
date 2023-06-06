package com.prmto.poxedexkmm.splash.data

import com.prmto.poxedexkmm.splash.domain.repo.KeyValueLocalRepo
import com.russhwolf.settings.Settings

class SettingKeyValueImpl(
    private val settings: Settings
) : KeyValueLocalRepo {

    companion object PreferencesKeys {
        private const val ONBOARDING = "onboarding"
    }

    override fun getOnboardingState(): Boolean {
        return settings.getBoolean(ONBOARDING, false)
    }

    override fun saveOnboardingState(state: Boolean) {
        settings.putBoolean(ONBOARDING, state)
    }
}