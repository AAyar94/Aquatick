package com.aayar94.aquatick.data.repository

import com.aayar94.aquatick.data.local.user.UserDao
import com.aayar94.aquatick.data.model.user.UserDataModel
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUserData(): UserDataModel {
        return userDao.getUserData()
    }

    suspend fun updateUserData(data: UserDataModel) {
        return userDao.updateUserData(data)
    }

}