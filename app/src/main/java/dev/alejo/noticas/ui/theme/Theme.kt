import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.alejo.noticas.ui.theme.backgroundDark
import dev.alejo.noticas.ui.theme.backgroundLight
import dev.alejo.noticas.ui.theme.errorContainerDark
import dev.alejo.noticas.ui.theme.errorContainerLight
import dev.alejo.noticas.ui.theme.errorDark
import dev.alejo.noticas.ui.theme.errorLight
import dev.alejo.noticas.ui.theme.inverseOnSurfaceDark
import dev.alejo.noticas.ui.theme.inverseOnSurfaceLight
import dev.alejo.noticas.ui.theme.inversePrimaryDark
import dev.alejo.noticas.ui.theme.inversePrimaryLight
import dev.alejo.noticas.ui.theme.inverseSurfaceDark
import dev.alejo.noticas.ui.theme.inverseSurfaceLight
import dev.alejo.noticas.ui.theme.onBackgroundDark
import dev.alejo.noticas.ui.theme.onBackgroundLight
import dev.alejo.noticas.ui.theme.onErrorContainerDark
import dev.alejo.noticas.ui.theme.onErrorContainerLight
import dev.alejo.noticas.ui.theme.onErrorDark
import dev.alejo.noticas.ui.theme.onErrorLight
import dev.alejo.noticas.ui.theme.onPrimaryContainerDark
import dev.alejo.noticas.ui.theme.onPrimaryContainerLight
import dev.alejo.noticas.ui.theme.onPrimaryDark
import dev.alejo.noticas.ui.theme.onPrimaryLight
import dev.alejo.noticas.ui.theme.onSecondaryContainerDark
import dev.alejo.noticas.ui.theme.onSecondaryContainerLight
import dev.alejo.noticas.ui.theme.onSecondaryDark
import dev.alejo.noticas.ui.theme.onSecondaryLight
import dev.alejo.noticas.ui.theme.onSurfaceDark
import dev.alejo.noticas.ui.theme.onSurfaceLight
import dev.alejo.noticas.ui.theme.onSurfaceVariantDark
import dev.alejo.noticas.ui.theme.onSurfaceVariantLight
import dev.alejo.noticas.ui.theme.onTertiaryContainerDark
import dev.alejo.noticas.ui.theme.onTertiaryContainerLight
import dev.alejo.noticas.ui.theme.onTertiaryDark
import dev.alejo.noticas.ui.theme.onTertiaryLight
import dev.alejo.noticas.ui.theme.outlineDark
import dev.alejo.noticas.ui.theme.outlineLight
import dev.alejo.noticas.ui.theme.outlineVariantDark
import dev.alejo.noticas.ui.theme.outlineVariantLight
import dev.alejo.noticas.ui.theme.primaryContainerDark
import dev.alejo.noticas.ui.theme.primaryContainerLight
import dev.alejo.noticas.ui.theme.primaryDark
import dev.alejo.noticas.ui.theme.primaryLight
import dev.alejo.noticas.ui.theme.scrimDark
import dev.alejo.noticas.ui.theme.scrimLight
import dev.alejo.noticas.ui.theme.secondaryContainerDark
import dev.alejo.noticas.ui.theme.secondaryContainerLight
import dev.alejo.noticas.ui.theme.secondaryDark
import dev.alejo.noticas.ui.theme.secondaryLight
import dev.alejo.noticas.ui.theme.surfaceBrightDark
import dev.alejo.noticas.ui.theme.surfaceBrightLight
import dev.alejo.noticas.ui.theme.surfaceContainerDark
import dev.alejo.noticas.ui.theme.surfaceContainerHighDark
import dev.alejo.noticas.ui.theme.surfaceContainerHighLight
import dev.alejo.noticas.ui.theme.surfaceContainerHighestDark
import dev.alejo.noticas.ui.theme.surfaceContainerHighestLight
import dev.alejo.noticas.ui.theme.surfaceContainerLight
import dev.alejo.noticas.ui.theme.surfaceContainerLowDark
import dev.alejo.noticas.ui.theme.surfaceContainerLowLight
import dev.alejo.noticas.ui.theme.surfaceContainerLowestDark
import dev.alejo.noticas.ui.theme.surfaceContainerLowestLight
import dev.alejo.noticas.ui.theme.surfaceDark
import dev.alejo.noticas.ui.theme.surfaceDimDark
import dev.alejo.noticas.ui.theme.surfaceDimLight
import dev.alejo.noticas.ui.theme.surfaceLight
import dev.alejo.noticas.ui.theme.surfaceVariantDark
import dev.alejo.noticas.ui.theme.surfaceVariantLight
import dev.alejo.noticas.ui.theme.tertiaryContainerDark
import dev.alejo.noticas.ui.theme.tertiaryContainerLight
import dev.alejo.noticas.ui.theme.tertiaryDark
import dev.alejo.noticas.ui.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark
)

@Composable
fun NoticasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}