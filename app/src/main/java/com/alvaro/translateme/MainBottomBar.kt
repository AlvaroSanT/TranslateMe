package com.alvaro.translateme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.alvaro.translateme.navigation.Route
import com.alvaro.ui.R
import com.alvaro.ui.components.BottomNavigationBar
import com.alvaro.ui.components.NavigationItem

@Composable
fun MainBottomBar(
    currentTab: Route,
    onTabSelected: (Route) -> Unit,
    modifier: Modifier = Modifier
) {
    val navItems = TopLevelDestination.entries.map { destination ->
        NavigationItem(
            label = stringResource(destination.label),
            icon = destination.icon,
            isSelected = currentTab == destination.route,
            onClick = { onTabSelected(destination.route) }
        )
    }

    BottomNavigationBar(
        items = navItems,
        modifier = modifier
    )
}

enum class TopLevelDestination(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: Route
) {
    COLLECTIONS(
        label = R.string.collections,
        icon = R.drawable.ic_collections,
        route = Route.CollectionsRoute
    ),
    PRACTICE(
        label = R.string.practice,
        icon = R.drawable.ic_test,
        route = Route.PracticeRoute
    ),
    STATISTICS(
        label = R.string.statistics,
        icon = R.drawable.ic_statistic,
        route = Route.StatisticsRoute
    )
}