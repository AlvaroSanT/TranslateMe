package user

import android.util.Log
import user.model.UserLanguage
import javax.inject.Inject

class SaveUserLanguageUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(code: String, name: String): Result<Unit> {
        Log.d("SaveUserLanguageUseCase", "Saving language: $name ($code)")
        return getUserIdUseCase().fold(
            onSuccess = { userId ->
                val userLanguage = UserLanguage(
                    code = code,
                    name = name,
                    updatedAt = System.currentTimeMillis()
                )
                val result = userRepository.saveSelectedLanguage(userId, userLanguage)
                result.onSuccess {
                    Log.d("SaveUserLanguageUseCase", "Language saved successfully for user: $userId")
                }.onFailure {
                    Log.e("SaveUserLanguageUseCase", "Failed to save language for user: $userId", it)
                }
                result
            },
            onFailure = { 
                Log.e("SaveUserLanguageUseCase", "Failed to get userId to save language", it)
                Result.failure(it) 
            }
        )
    }
}
