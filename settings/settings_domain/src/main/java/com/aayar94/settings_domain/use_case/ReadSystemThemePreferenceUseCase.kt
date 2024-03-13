package com.aayar94.settings_domain.use_case

import com.aayar94.core_ui.util.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadSystemThemePreferenceUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return dataStoreRepository.useSystemThemeFlow
    }
}