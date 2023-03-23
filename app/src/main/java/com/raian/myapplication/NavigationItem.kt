package com.raian.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val label: String, val icons: ImageVector){
    object Home : NavigationItem("home", "Home", Icons.Default.Home)
    object Notifications :
        NavigationItem("notifications", "Notifications", Icons.Default.Notifications)

    object Profile: NavigationItem("profile","Profile",Icons.Default.AccountCircle)
    
    object Settings : NavigationItem("setting","Settings",Icons.Default.Settings)
    fun createRoute(id:Int) = "detail/${id}"
}
