package com.alvaro.ui.components

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.tooling.preview.Preview
import com.alvaro.ui.ColorBackground
import com.alvaro.ui.ColorPrimary
import com.alvaro.ui.ColorUnselectedTabBarItem
import com.alvaro.ui.FontSize
import com.alvaro.ui.R
import com.alvaro.ui.Size

/**
 * Data class representing an item in the bottom navigation bar.
 * This makes the component agnostic of actual navigation routes.
 */
data class NavigationItem(
    val label: String,
    @DrawableRes val icon: Int,
    val isSelected: Boolean,
    val onClick: () -> Unit
)

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = ColorBackground,
        windowInsets = WindowInsets.navigationBars,
        modifier = modifier
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.isSelected,
                onClick = item.onClick,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        modifier = Modifier.size(Size.XL)
                    )
                },
                label = {
                    Text(
                        text = item.label,
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

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        items = listOf(
            NavigationItem(
                label = "Practice",
                icon = R.drawable.ic_test,
                isSelected = true,
                onClick = {}
            ),
            NavigationItem(
                label = "Collections",
                icon = R.drawable.ic_collections,
                isSelected = false,
                onClick = {}
            ),
            NavigationItem(
                label = "Statistics",
                icon = R.drawable.ic_statistic,
                isSelected = false,
                onClick = {}
            )
        )
    )
}
