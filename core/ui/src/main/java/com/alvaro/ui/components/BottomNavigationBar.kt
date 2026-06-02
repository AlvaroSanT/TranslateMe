package com.alvaro.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alvaro.ui.ColorBackground
import com.alvaro.ui.ColorPrimary
import com.alvaro.ui.ColorUnselectedTabBarItem
import com.alvaro.ui.FontSize
import com.alvaro.ui.R
import com.alvaro.ui.Size

@Composable
fun BottomNavigationBar(
    // navController: NavController
) {
    val tabBarItems = listOf(
        TabBarItem(
            title = R.string.collections,
            icon = R.drawable.ic_collections,
            route = "home" // HomeRoute.Main
        ),
        TabBarItem(
            title = R.string.practice,
            icon = R.drawable.ic_test,
            route = "practice" // MyTripsRoute.Graph
        ),
        TabBarItem(
            title = R.string.statistics,
            icon = R.drawable.ic_statistic,
            route = "statistics" // ProfileRoute.Graph
        )
    )
    NavigationBar(
        containerColor = ColorBackground,
        windowInsets = WindowInsets.navigationBars
    ) {
        // val navBackStackEntry by navController.currentBackStackEntryAsState()
        // val currentDestination = navBackStackEntry?.destination

        tabBarItems.forEachIndexed { index, tabBarItem ->
            // Check if this tab is currently selected by checking the navigation hierarchy
            val isSelected = index == 0
            /*
                currentDestination?.hierarchy?.any { it.route == tabBarItem.route::class.qualifiedName } == true
            */

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    // Only navigate if not already on this destination to avoid redundant navigation
                    /*
                    if (!isSelected) {
                        navController.navigate(tabBarItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    */
                },
                icon = {
                    Icon(
                        painter = painterResource(id = tabBarItem.icon),
                        contentDescription = stringResource(id = tabBarItem.title),
                        modifier = Modifier.size(Size.XL)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = tabBarItem.title),
                        fontSize = FontSize.S
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = ColorPrimary,
                    selectedTextColor = ColorPrimary,
                    selectedIndicatorColor = ColorBackground,
                    unselectedIconColor = ColorUnselectedTabBarItem,
                    unselectedTextColor = ColorUnselectedTabBarItem,
                    disabledIconColor = ColorUnselectedTabBarItem,
                    disabledTextColor = ColorUnselectedTabBarItem
                )
            )
        }
    }
}

@Composable
@Preview
fun BottomNavigationBarPreview() {
    BottomNavigationBar(/*navController = rememberNavController()*/)
}


data class TabBarItem(
    @StringRes
    val title: Int,
    @DrawableRes
    val icon: Int,
    val route: Any
)