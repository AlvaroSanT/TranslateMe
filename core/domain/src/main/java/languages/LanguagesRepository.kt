package languages

interface LanguagesRepository {
    fun getAllLanguages(): List<String>
}