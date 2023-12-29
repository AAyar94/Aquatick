package com.aayar94.aquatick.domain.usecase

import com.aayar94.aquatick.data.repository.UserDataRepository
import com.aayar94.aquatick.domain.model.SetupScreenModel
import javax.inject.Inject

class GetUserDataUserCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(): SetupScreenModel {
        val response = userDataRepository.getUserData()
        return SetupScreenModel(
            name = response.name,
            age = response.age,
            weight = response.weight,
            gender = response.gender,
            activityLevel = activityLevelDecoder(response.activityLevel)
        )
    }

}


fun activityLevelDecoder(value: Int): String {
    return when (value) {
        5 -> "Very active"
        4 -> "Active"
        3 -> "Average"
        4 -> "Less than average"
        else -> "Not active"
    }
}