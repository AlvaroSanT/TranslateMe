package user

import android.util.Log
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<String> {
        Log.d("GetUserIdUseCase", "Invoking GetUserIdUseCase")
        val currentId = userRepository.getCurrentUserId()
        return if (currentId != null) {
            Log.d("GetUserIdUseCase", "User already signed in with ID: $currentId")
            Result.success(currentId)
        } else {
            Log.d("GetUserIdUseCase", "User not signed in, signing in anonymously")
            val result = userRepository.signInAnonymously()
            result.onSuccess { 
                Log.d("GetUserIdUseCase", "Anonymous sign in successful with ID: $it")
            }.onFailure {
                Log.e("GetUserIdUseCase", "Anonymous sign in failed", it)
            }
            result
        }
    }
}
