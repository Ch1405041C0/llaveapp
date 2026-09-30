package com.example.llave360.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val InstagramIcon: ImageVector by lazy {
  ImageVector.Builder(
    name = "Instagram",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
  ).path(
    fill = null,
    stroke = SolidColor(Color.White),
    strokeLineWidth = 2f
  ) {
    moveTo(7f, 2f)
    horizontalLineTo(17f)
    curveTo(19.76f, 2f, 22f, 4.24f, 22f, 7f)
    verticalLineTo(17f)
    curveTo(22f, 19.76f, 19.76f, 22f, 17f, 22f)
    horizontalLineTo(7f)
    curveTo(4.24f, 22f, 2f, 19.76f, 2f, 17f)
    verticalLineTo(7f)
    curveTo(2f, 4.24f, 4.24f, 2f, 7f, 2f)
    close()
  }.path(
    fill = null,
    stroke = SolidColor(Color.White),
    strokeLineWidth = 2f
  ) {
    moveTo(12f, 7f)
    curveTo(14.76f, 7f, 17f, 9.24f, 17f, 12f)
    curveTo(17f, 14.76f, 14.76f, 17f, 12f, 17f)
    curveTo(9.24f, 17f, 7f, 14.76f, 7f, 12f)
    curveTo(7f, 9.24f, 9.24f, 7f, 12f, 7f)
    close()
  }.path(
    fill = SolidColor(Color.White)
  ) {
    moveTo(17.5f, 6f)
    curveTo(17.78f, 6f, 18f, 6.22f, 18f, 6.5f)
    curveTo(18f, 6.78f, 17.78f, 7f, 17.5f, 7f)
    curveTo(17.22f, 7f, 17f, 6.78f, 17f, 6.5f)
    curveTo(17f, 6.22f, 17.22f, 6f, 17.5f, 6f)
    close()
  }.build()
}

val FacebookIcon: ImageVector by lazy {
  ImageVector.Builder(
    name = "Facebook",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
  ).path(
    fill = SolidColor(Color.White)
  ) {
    moveTo(22f, 12f)
    curveTo(22f, 6.48f, 17.52f, 2f, 12f, 2f)
    reflectiveCurveTo(2f, 6.48f, 2f, 12f)
    curveTo(2f, 16.99f, 5.64f, 21.13f, 10.44f, 21.89f)
    verticalLineTo(14.89f)
    horizontalLineTo(7.89f)
    verticalLineTo(12f)
    horizontalLineTo(10.44f)
    verticalLineTo(9.8f)
    curveTo(10.44f, 7.28f, 11.93f, 5.89f, 14.24f, 5.89f)
    curveTo(15.35f, 5.89f, 16.45f, 6f, 16.45f, 6f)
    verticalLineTo(8.49f)
    horizontalLineTo(15.17f)
    curveTo(13.92f, 8.49f, 13.53f, 9.27f, 13.53f, 10.06f)
    verticalLineTo(12f)
    horizontalLineTo(16.34f)
    lineTo(15.89f, 14.89f)
    horizontalLineTo(13.53f)
    verticalLineTo(21.89f)
    curveTo(18.36f, 21.13f, 22f, 16.99f, 22f, 12f)
    close()
  }.build()
}

val TikTokIcon: ImageVector by lazy {
  ImageVector.Builder(
    name = "TikTok",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
  ).path(
    fill = SolidColor(Color.White)
  ) {
    moveTo(12.53f, 2f)
    verticalLineTo(14.11f)
    curveTo(12.53f, 16.53f, 10.56f, 18.5f, 8.14f, 18.5f)
    reflectiveCurveTo(3.75f, 16.53f, 3.75f, 14.11f)
    reflectiveCurveTo(5.72f, 9.72f, 8.14f, 9.72f)
    curveTo(8.52f, 9.72f, 8.89f, 9.77f, 9.24f, 9.87f)
    verticalLineTo(5.95f)
    curveTo(8.88f, 5.91f, 8.51f, 5.89f, 8.14f, 5.89f)
    curveTo(3.64f, 5.89f, 0f, 9.53f, 0f, 14.03f)
    reflectiveCurveTo(3.64f, 22.17f, 8.14f, 22.17f)
    curveTo(12.64f, 22.17f, 16.28f, 18.53f, 16.28f, 14.03f)
    verticalLineTo(7.8f)
    curveTo(18.56f, 9.38f, 21.36f, 10.15f, 24f, 9.89f)
    verticalLineTo(6f)
    curveTo(21.96f, 6f, 20.01f, 5.17f, 18.57f, 3.71f)
    curveTo(17.5f, 2.62f, 16.78f, 1.34f, 16.54f, 0f)
    horizontalLineTo(12.53f)
    verticalLineTo(2f)
    close()
  }.build()
}
