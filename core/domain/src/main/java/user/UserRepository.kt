package user

import user.model.UserLanguage

interface UserRepository {
    suspend fun saveSelectedLanguage(userId: String, language: UserLanguage): Result<Unit>
    suspend fun getLatestSelectedLanguage(userId: String): Result<UserLanguage?>
    fun getCurrentUserId(): String?
    suspend fun signInAnonymously(): Result<String>
}
