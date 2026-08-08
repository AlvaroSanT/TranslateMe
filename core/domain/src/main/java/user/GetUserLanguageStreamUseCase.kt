package user

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import user.model.UserLanguage
import javax.inject.Inject

class GetUserLanguageStreamUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(): Flow<UserLanguage?> {
        Log.d("GetUserLanguageStreamUseCase", "Invoking GetUserLanguageStreamUseCase")
        return getUserIdUseCase().fold(
            onSuccess = { userId ->
                Log.d("GetUserLanguageStreamUseCase", "Getting language stream for user: $userId")
                userRepository.getSelectedLanguageStream(userId).onEach { language ->
                    Log.d("GetUserLanguageStreamUseCase", "Language stream emission: $language")
                }
            },
            onFailure = { 
                Log.e("GetUserLanguageStreamUseCase", "Failed to get userId for language stream", it)
                flowOf(null) 
            }
        )
    }
}
