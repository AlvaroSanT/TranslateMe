package com.alvaro.data.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import user.UserRepository
import user.model.UserLanguage
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserRepository {

    override suspend fun saveSelectedLanguage(userId: String, language: UserLanguage): Result<Unit> {
        return try {
            firestore.collection("users")
                .document(userId)
                .collection("languages")
                .document(language.code)
                .set(language)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLatestSelectedLanguage(userId: String): Result<UserLanguage?> {
        return try {
            val querySnapshot = firestore.collection("users")
                .document(userId)
                .collection("languages")
                .orderBy("updatedAt", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()
            
            val language = querySnapshot.documents.firstOrNull()?.toObject(UserLanguage::class.java)
            Result.success(language)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun signInAnonymously(): Result<String> {
        return try {
            val result = auth.signInAnonymously().await()
            val userId = result.user?.uid
            if (userId != null) {
                Result.success(userId)
            } else {
                Result.failure(Exception("User is null after anonymous sign in"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
