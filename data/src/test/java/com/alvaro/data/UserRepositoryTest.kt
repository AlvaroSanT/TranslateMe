package com.alvaro.data

import com.alvaro.data.user.UserRepositoryImpl
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import user.model.UserLanguage

class UserRepositoryTest {

    @Mock
    private lateinit var firestore: FirebaseFirestore

    @Mock
    private lateinit var auth: FirebaseAuth

    @Mock
    private lateinit var collectionReference: CollectionReference

    @Mock
    private lateinit var languagesCollectionReference: CollectionReference

    @Mock
    private lateinit var documentReference: DocumentReference

    @Mock
    private lateinit var languageDocumentReference: DocumentReference

    @Mock
    private lateinit var voidTask: Task<Void>

    private lateinit var userRepository: UserRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userRepository = UserRepositoryImpl(firestore, auth)
    }

    @Test
    fun `saveSelectedLanguage should return success when firestore operation succeeds`() = runBlocking {
        val userId = "user123"
        val language = UserLanguage("en", "English", 123456789L)

        `when`(firestore.collection("users")).thenReturn(collectionReference)
        `when`(collectionReference.document(userId)).thenReturn(documentReference)
        `when`(documentReference.collection("languages")).thenReturn(languagesCollectionReference)
        `when`(languagesCollectionReference.document(language.code)).thenReturn(languageDocumentReference)
        `when`(languageDocumentReference.set(language)).thenReturn(voidTask)
        `when`(voidTask.isComplete).thenReturn(true)
        `when`(voidTask.isSuccessful).thenReturn(true)

        val result = userRepository.saveSelectedLanguage(userId, language)

        assertTrue(result.isSuccess)
    }
}
