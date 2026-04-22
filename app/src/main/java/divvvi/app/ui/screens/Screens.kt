package divvvi.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import divvvi.app.ui.Routes

@Composable
private fun Stub(title: String, nav: NavController, nextRoute: String?, nextLabel: String = "Next") {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title)
        if (nextRoute != null) {
            Button(onClick = { nav.navigate(nextRoute) }) { Text(nextLabel) }
        }
    }
}

@Composable fun SplashScreen(nav: NavController) = Stub("Divvvi", nav, Routes.GROUP, "Get started")
@Composable fun GroupScreen(nav: NavController) = Stub("Group", nav, Routes.SCAN, "Scan receipt")
@Composable fun ScanScreen(nav: NavController) = Stub("Scan", nav, Routes.REVIEW)
@Composable fun ReviewScreen(nav: NavController) = Stub("Review", nav, Routes.ASSIGN)
@Composable fun AssignScreen(nav: NavController) = Stub("Assign", nav, Routes.CONFIRM)
@Composable fun ConfirmScreen(nav: NavController) = Stub("Confirm", nav, Routes.HISTORY, "Save")
@Composable fun HistoryScreen(nav: NavController) = Stub("History", nav, null)
