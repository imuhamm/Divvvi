package com.divvvi.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.divvvi.app.ui.screens.AssignScreen
import com.divvvi.app.ui.screens.ConfirmScreen
import com.divvvi.app.ui.screens.GroupScreen
import com.divvvi.app.ui.screens.HistoryScreen
import com.divvvi.app.ui.screens.ReviewScreen
import com.divvvi.app.ui.screens.ScanScreen
import com.divvvi.app.ui.screens.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val GROUP = "group"
    const val SCAN = "scan"
    const val REVIEW = "review"
    const val ASSIGN = "assign"
    const val CONFIRM = "confirm"
    const val HISTORY = "history"
}

@Composable
fun DivvviApp() {
    val nav = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = nav, startDestination = Routes.SPLASH) {
            composable(Routes.SPLASH) { SplashScreen(nav) }
            composable(Routes.GROUP) { GroupScreen(nav) }
            composable(Routes.SCAN) { ScanScreen(nav) }
            composable(Routes.REVIEW) { ReviewScreen(nav) }
            composable(Routes.ASSIGN) { AssignScreen(nav) }
            composable(Routes.CONFIRM) { ConfirmScreen(nav) }
            composable(Routes.HISTORY) { HistoryScreen(nav) }
        }
    }
}
