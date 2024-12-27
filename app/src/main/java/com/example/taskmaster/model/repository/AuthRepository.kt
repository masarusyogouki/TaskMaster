package com.example.taskmaster.model.repository

import com.example.taskmaster.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun register(
        email: String,
        password: String,
    )

    suspend fun signIn(
        email: String,
        password: String,
    )

    suspend fun logOut()

    suspend fun sendRecoveryEmail(email: String)
}
