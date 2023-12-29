package com.aayar94.aquatick.domain.usecase

import com.aayar94.aquatick.data.repository.UserDataRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke(): String {
        val response = userDataRepository.getUserData()
        return response.name
    }

}