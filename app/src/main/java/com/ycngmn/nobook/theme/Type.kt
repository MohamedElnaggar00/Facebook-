// theme/Color.kt
package com.ycngmn.nobook.theme
import androidx.compose.ui.graphics.Color

val FacebookBlue = Color(0xFF1877F2)
val BackgroundLight = Color(0xFFFFFFFF)
val BackgroundDark = Color(0xFF18191A)

// theme/Type.kt
package com.ycngmn.nobook.theme
import androidx.compose.material3.Typography

val Typography = Typography()

// theme/Theme.kt
package com.ycngmn.nobook.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = FacebookBlue,
    background = BackgroundDark,
    surface = BackgroundDark
)

private val LightColorScheme = lightColorScheme(
    primary = FacebookBlue,
    background = BackgroundLight,
    surface = BackgroundLight
)

@Composable
fun NobookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
