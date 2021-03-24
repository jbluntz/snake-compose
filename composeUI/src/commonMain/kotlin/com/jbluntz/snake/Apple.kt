package com.jbluntz.snake

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate

fun DrawScope.drawApple(centerX: Float, centerY: Float, radius: Float) {

    //as drawn, apple is defaultWidth = 106.06.dp, defaultHeight = 122.88.dp, center is roughly 53:73
    scale(radius / 53f, Offset(centerX, centerY + 20f)) {
        translate(left = centerX - 53f, top = centerY - 73f) {
            Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(41.57f, 31.85f)
                cubicTo(7.85f, 21.26f, -10.8f, 60.03f, 6.67f, 90.74f)
                relativeCubicTo(5.23f, 9.19f, 11.57f, 18.65f, 21.4f, 27.25f)
                relativeCubicTo(9.35f, 6.7f, 17.58f, 5.78f, 25.19f, 1.63f)
                relativeCubicTo(6.05f, 2.87f, 12.45f, 4.01f, 19.33f, 1.43f)
                relativeCubicTo(10.11f, -3.8f, 20.41f, -18.8f, 25.26f, -28.07f)
                relativeCubicTo(10.6f, -20.27f, 14.2f, -46.28f, -9.44f, -58.96f)
                relativeCubicTo(-6.45f, -3.46f, -14.52f, -4.47f, -24.74f, -2.18f)
                cubicTo(56.87f, 34.18f, 48.42f, 33.94f, 41.57f, 31.85f)
                lineTo(41.57f, 31.85f)
                close()
            }.also { drawPath(it, Color(0xFFD3141B)) }

            Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(33.99f, 28.12f)
                relativeCubicTo(5.16f, 0.64f, 10.71f, -0.55f, 16.69f, -3.75f)
                relativeLineTo(1.14f, -5.83f)
                cubicTo(45.3f, 7.04f, 34.29f, 1.09f, 16.17f, 3.86f)
                lineTo(9.05f, 4.61f)
                cubicTo(14.26f, 15.44f, 20.69f, 26.49f, 33.99f, 28.12f)
                lineTo(33.99f, 28.12f)
                lineTo(33.99f, 28.12f)
                close()
            }.also { drawPath(it, Color(0xFF7DB820)) }

            Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(17.3f, 8.52f)
                relativeCubicTo(11.85f, 4.1f, 19.55f, 5.74f, 29.5f, 13.44f)
                cubicTo(35.06f, 18.1f, 27.61f, 15.57f, 17.3f, 8.52f)
                lineTo(17.3f, 8.52f)
                close()
            }.also { drawPath(it, Color(0xFF537918)) }

            Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(50.08f, 25.82f)
                relativeLineTo(0.07f, -0.04f)
                relativeCubicTo(-0.34f, 2.54f, -0.49f, 4.89f, -0.46f, 7.69f)
                lineTo(56.0f, 33.6f)
                relativeCubicTo(-0.07f, -5.33f, 0.62f, -9.87f, 1.99f, -14.06f)
                relativeCubicTo(1.83f, -5.58f, 4.92f, -10.23f, 9.07f, -14.07f)
                relativeCubicTo(1.28f, -1.18f, 1.36f, -3.17f, 0.19f, -4.45f)
                relativeCubicTo(-1.18f, -1.28f, -3.17f, -1.36f, -4.45f, -0.19f)
                relativeCubicTo(-4.95f, 4.57f, -8.61f, 10.1f, -10.8f, 16.76f)
                relativeCubicTo(-0.28f, 0.86f, -0.55f, 1.75f, -0.78f, 2.65f)
                relativeLineTo(-0.04f, -0.07f)
                lineTo(50.08f, 25.82f)
                lineTo(50.08f, 25.82f)
                close()
            }.also { drawPath(it, Color(0xFF543429)) }

            Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(16.87f, 78.58f)
                relativeCubicTo(1.25f, -0.42f, 2.61f, 0.25f, 3.03f, 1.51f)
                relativeCubicTo(1.35f, 4.04f, 3.37f, 7.67f, 5.88f, 10.98f)
                relativeCubicTo(2.56f, 3.35f, 5.63f, 6.38f, 9.07f, 9.18f)
                relativeCubicTo(1.02f, 0.83f, 1.18f, 2.34f, 0.35f, 3.37f)
                relativeCubicTo(-0.83f, 1.02f, -2.34f, 1.18f, -3.37f, 0.35f)
                relativeCubicTo(-3.73f, -3.03f, -7.06f, -6.33f, -9.85f, -9.99f)
                relativeCubicTo(-2.83f, -3.71f, -5.09f, -7.79f, -6.62f, -12.36f)
                cubicTo(14.95f, 80.36f, 15.62f, 79.01f, 16.87f, 78.58f)
                lineTo(16.87f, 78.58f)
                lineTo(16.87f, 78.58f)
                close()
            }.also { drawPath(it, Color(0xFFFFFFFF)) }
        }
    }
}