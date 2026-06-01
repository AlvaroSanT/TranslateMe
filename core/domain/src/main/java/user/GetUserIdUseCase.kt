package user

import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<String> {
        val currentId = userRepository.getCurrentUserId()
        return if (currentId != null) {
            Result.success(currentId)
        } else {
            userRepository.signInAnonymously()
        }
    }
}
