package com.prmto.poxedexkmm.android.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.prmto.poxedexkmm.android.R

data class BottomNavigationItem(
    val selected: Boolean = false,
    val route: String,
    @StringRes val labelTextRes: Int,
    @DrawableRes val selectedIconDrawable: Int,
    @DrawableRes val unSelectedIconDrawable: Int
)


@Composable
fun rememberBottomNavigationItems() = remember {
    mutableStateOf(bottomNavigationItems)
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        selected = true,
        labelTextRes = R.string.pokemon,
        route = Screens.HomeScreen.route,
        selectedIconDrawable = R.drawable.home_selected,
        unSelectedIconDrawable = R.drawable.home_unselected,
    ),
    BottomNavigationItem(
        labelTextRes = R.string.favorite,
        route = Screens.FavoriteScreen.route,
        selectedIconDrawable = R.drawable.favorite_selected,
        unSelectedIconDrawable = R.drawable.favorite_unselected,
    )
)