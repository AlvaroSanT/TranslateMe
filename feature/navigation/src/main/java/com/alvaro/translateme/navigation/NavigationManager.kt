package com.alvaro.translateme.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Gestor de navegación que mantiene el estado de la pila de retroceso (back stack).
 * Implementa una pila única donde los cambios de pestaña se añaden al historial.
 */
@Singleton
class NavigationManager @Inject constructor() {
    
    // Pila única de navegación (Source of Truth)
    private val _backStack = mutableStateListOf<Route>(Route.PracticeRoute)
    val backStack: List<Route> = _backStack

    /**
     * Identifica la pestaña actualmente activa buscando la ruta raíz más reciente en la pila.
     */
    val currentTab: State<Route> = derivedStateOf {
        _backStack.lastOrNull { 
            it is Route.PracticeRoute || it is Route.CollectionsRoute || it is Route.StatisticsRoute 
        } ?: Route.PracticeRoute
    }

    /**
     * Cambia la pestaña actual añadiéndola a la pila si no es ya la última.
     */
    private fun switchTab(tab: Route) {
        if (_backStack.lastOrNull() != tab) {
            _backStack.add(tab)
        } else {
            val tabIndex = _backStack.indexOfLast { it == tab }
            if (tabIndex != -1 && tabIndex < _backStack.size - 1) {
                _backStack.subList(tabIndex + 1, _backStack.size).clear()
            }
        }
    }

    /**
     * Navega a una nueva ruta en la pila única.
     */
    fun navigateTo(route: Route) {
        if (isRootRoute(route)) {
            switchTab(route)
        } else {
            _backStack.add(route)
        }
    }

    /**
     * Retrocede a la pantalla anterior en la pila única.
     */
    fun goBack(): Boolean {
        return if (_backStack.size > 1) {
            _backStack.removeAt(_backStack.size - 1)
            true
        } else {
            false
        }
    }

    private fun isRootRoute(route: Route): Boolean {
        return route is Route.PracticeRoute || route is Route.CollectionsRoute || route is Route.StatisticsRoute
    }
}
