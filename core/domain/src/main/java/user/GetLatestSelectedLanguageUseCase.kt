package user

import android.util.Log
import javax.inject.Inject

class GetLatestSelectedLanguageUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(): Result<String?> {
        Log.d("GetLatestSelectedLanguageUseCase", "Invoking GetLatestSelectedLanguageUseCase")
        return getUserIdUseCase().fold(
            onSuccess = { userId ->
                Log.d("GetLatestSelectedLanguageUseCase", "Fetching latest language for user: $userId")
                val result = userRepository.getLatestSelectedLanguage(userId).map { it?.code }
                result.onSuccess {
                    Log.d("GetLatestSelectedLanguageUseCase", "Latest language code fetched: $it")
                }.onFailure {
                    Log.e("GetLatestSelectedLanguageUseCase", "Failed to fetch latest language", it)
                }
                result
            },
            onFailure = { 
                Log.e("GetLatestSelectedLanguageUseCase", "Failed to get userId for latest language", it)
                Result.failure(it) 
            }
        )
    }
}
