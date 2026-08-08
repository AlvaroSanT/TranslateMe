package user

import kotlinx.coroutines.flow.Flow
import user.model.UserLanguage

interface UserRepository {
    fun getSelectedLanguageStream(userId: String): Flow<UserLanguage?>
    suspend fun saveSelectedLanguage(userId: String, language: UserLanguage): Result<Unit>
    suspend fun getLatestSelectedLanguage(userId: String): Result<UserLanguage?>
    fun getCurrentUserId(): String?
    suspend fun signInAnonymously(): Result<String>
}
