package com.aayar94.settings_domain.use_case

import com.aayar94.core_ui.util.DataStoreRepository
import javax.inject.Inject

class SelectColorSchemeUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(colorScheme: String) {
        return dataStoreRepository.saveSelectedColorScheme(colorScheme)
    }

}