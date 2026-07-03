package com.alvaro.translateme.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Representa las diferentes pantallas o destinos en la aplicación.
 */
sealed interface Route: NavKey {
    @Serializable
    data object CollectionsRoute : Route

    @Serializable
    data object PracticeRoute : Route
    
    // Ejemplo de ruta con argumentos
    @Serializable
    data object StatisticsRoute : Route
}
