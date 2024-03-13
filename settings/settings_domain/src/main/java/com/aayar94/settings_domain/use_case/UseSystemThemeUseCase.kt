package com.aayar94.settings_domain.use_case

import com.aayar94.core_ui.util.DataStoreRepository
import javax.inject.Inject

class UseSystemThemeUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(boolean: Boolean) {
        return dataStoreRepository.saveUseSystemThemeMode(boolean)
    }

}