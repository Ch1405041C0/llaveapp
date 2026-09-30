package com.example.llave360.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val Llave360Colors = darkColorScheme(primary = Gold, onPrimary = Charcoal, secondary = GoldBright, background = Charcoal, onBackground = Ink, surface = SurfaceDark, onSurface = Ink, surfaceVariant = SurfaceVariantDark, onSurfaceVariant = MutedInk)

@Composable
fun Llave360Theme(content: @Composable () -> Unit) = MaterialTheme(colorScheme = Llave360Colors, typography = Typography, content = content)
