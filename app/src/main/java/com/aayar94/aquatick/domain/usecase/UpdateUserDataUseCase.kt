package com.aayar94.aquatick.domain.usecase

import com.aayar94.aquatick.data.model.user.UserDataModel
import com.aayar94.aquatick.data.repository.UserDataRepository
import com.aayar94.aquatick.domain.model.SetupScreenModel
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {


    suspend operator fun invoke(data: SetupScreenModel) {
        return userDataRepository.updateUserData(
            UserDataModel(
                age = data.age!!,
                weight = data.weight!!,
                gender = data.gender!!,
                activityLevel = when (data.activityLevel) {
                    "Very Active" -> 5
                    "Active" -> 4
                    "Average" -> 3
                    "Less than average" -> 2
                    else -> 1
                }
            )
        )
    }

}