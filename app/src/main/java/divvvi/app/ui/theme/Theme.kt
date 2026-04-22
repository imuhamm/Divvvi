package divvvi.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF3D5AFE),
    onPrimary = Color.White,
    secondary = Color(0xFF00C853),
    background = Color(0xFFFAFAFA),
    surface = Color.White,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8C9EFF),
    onPrimary = Color.Black,
    secondary = Color(0xFF69F0AE),
)

@Composable
fun DivvviTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = MaterialTheme.typography,
        content = content
    )
}
