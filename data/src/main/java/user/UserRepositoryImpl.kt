package com.alvaro.data.user

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import user.UserRepository
import user.model.UserLanguage
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserRepository {

    override fun getSelectedLanguageStream(userId: String): Flow<UserLanguage?> {
        Log.d("UserRepositoryImpl", "getSelectedLanguageStream for userId: $userId")
        return firestore.collection("users")
            .document(userId)
            .collection("languages")
            .orderBy("updatedAt", Query.Direction.DESCENDING)
            .limit(1)
            .snapshots()
            .map { querySnapshot ->
                querySnapshot.documents.firstOrNull()?.toObject(UserLanguage::class.java)
            }.onEach { language ->
                Log.d("UserRepositoryImpl", "Stream emission for $userId: $language")
            }
    }

    override suspend fun saveSelectedLanguage(userId: String, language: UserLanguage): Result<Unit> {
        Log.d("UserRepositoryImpl", "saveSelectedLanguage for userId: $userId, language: ${language.name}")
        return try {
            firestore.collection("users")
                .document(userId)
                .collection("languages")
                .document(language.code)
                .set(language)
                .await()
            Log.d("UserRepositoryImpl", "Successfully saved language for userId: $userId")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", "Error saving language for userId: $userId", e)
            Result.failure(e)
        }
    }

    override suspend fun getLatestSelectedLanguage(userId: String): Result<UserLanguage?> {
        Log.d("UserRepositoryImpl", "getLatestSelectedLanguage for userId: $userId")
        return try {
            val querySnapshot = firestore.collection("users")
                .document(userId)
                .collection("languages")
                .orderBy("updatedAt", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()
            
            val language = querySnapshot.documents.firstOrNull()?.toObject(UserLanguage::class.java)
            Log.d("UserRepositoryImpl", "Fetched latest language for $userId: $language")
            Result.success(language)
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", "Error fetching latest language for $userId", e)
            Result.failure(e)
        }
    }

    override fun getCurrentUserId(): String? {
        val uid = auth.currentUser?.uid
        Log.d("UserRepositoryImpl", "getCurrentUserId: $uid")
        return uid
    }

    override suspend fun signInAnonymously(): Result<String> {
        Log.d("UserRepositoryImpl", "signInAnonymously")
        return try {
            val result = auth.signInAnonymously().await()
            val userId = result.user?.uid
            if (userId != null) {
                Log.d("UserRepositoryImpl", "Anonymous sign in successful, userId: $userId")
                Result.success(userId)
            } else {
                Log.e("UserRepositoryImpl", "Anonymous sign in failed: User is null")
                Result.failure(Exception("User is null after anonymous sign in"))
            }
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", "Anonymous sign in exception", e)
            Result.failure(e)
        }
    }
}
