package com.alvaro.translateme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import com.alvaro.feature.collections.presentation.CollectionsScreen
import com.alvaro.feature.language.presentation.LanguagesScreen
import com.alvaro.feature.practice.presentation.PracticeScreen
import com.alvaro.feature.statistics.presentation.StatisticsScreen

/**
 * Composable que configura la visualización de la navegación.
 *
 * @param backStack La pila de navegación actual.
 * @param onBack Función que se ejecuta al presionar el botón de retroceso.
 * @param modifier Modificador para el contenedor.
 */
@Composable
fun MainNavDisplay(
    backStack: List<Route>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = onBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.CollectionsRoute> {
                CollectionsScreen()
            }
            entry<Route.PracticeRoute> {
                PracticeScreen()
            }
            entry<Route.LanguagesRoute> {
                LanguagesScreen()
            }
            entry<Route.StatisticsRoute> {
                StatisticsScreen()
            }
        }
    )
}
