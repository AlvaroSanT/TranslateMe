package user

import user.model.UserLanguage
import javax.inject.Inject

class SaveUserLanguageUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(code: String, name: String): Result<Unit> {
        return getUserIdUseCase().fold(
            onSuccess = { userId ->
                val userLanguage = UserLanguage(
                    code = code,
                    name = name,
                    updatedAt = System.currentTimeMillis()
                )
                userRepository.saveSelectedLanguage(userId, userLanguage)
            },
            onFailure = { Result.failure(it) }
        )
    }
}
