package user

import javax.inject.Inject

class GetLatestSelectedLanguageUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(): Result<String?> {
        return getUserIdUseCase().fold(
            onSuccess = { userId ->
                userRepository.getLatestSelectedLanguage(userId).map { it?.code }
            },
            onFailure = { Result.failure(it) }
        )
    }
}
