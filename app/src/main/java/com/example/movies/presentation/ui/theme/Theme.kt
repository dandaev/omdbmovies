package com.example.movies.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = BlueGrey80,
    onPrimary = BlueGrey20,
    primaryContainer = BlueGrey30,
    onPrimaryContainer = BlueGrey90,
    inversePrimary = BlueGrey40,
    secondary = LightBlue80,
    onSecondary = LightBlue20,
    secondaryContainer = LightBlue30,
    onSecondaryContainer = LightBlue90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Blue10,
    onBackground = Grey99,
    surface = LightBlue10,
    onSurface = Grey99,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = GreenGrey30,
    onSurfaceVariant = GreenGrey80,
    outline = GreenGrey80,
)

private val LightColorPalette = lightColorScheme(
    primary = BlueGrey40,
    onPrimary = Color.White,
    primaryContainer = BlueGrey90,
    onPrimaryContainer = BlueGrey10,
    inversePrimary = BlueGrey80,
    secondary = LightBlue40,
    onSecondary = Color.White,
    secondaryContainer = LightBlue90,
    onSecondaryContainer = LightBlue10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = GreenGrey90,
    onSurface = GreenGrey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = GreenGrey90,
    onSurfaceVariant = GreenGrey30,
    outline = GreenGrey50
)

@Composable
fun Material3AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
//    val colors = when {
//        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
//        darkTheme -> DarkColorPalette
//        else -> LightColorPalette
//    }

    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}