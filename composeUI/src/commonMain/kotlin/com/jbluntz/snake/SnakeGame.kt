package com.jbluntz.snake

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.jbluntz.snake.model.Point
import com.jbluntz.snake.model.Snake

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SnakeGame(viewModel: SnakeViewModel) {
    val snake: Snake by viewModel.snake.collectAsState()
    val apple: Point? by viewModel.apple.collectAsState()
    val dead: Boolean by viewModel.dead.collectAsState(false)
    Box(modifier = Modifier.fillMaxSize().swipeable { viewModel.turn(it) }) {
        AnimatedVisibility(
            visible = dead,
            enter = fadeIn(animationSpec = tween(durationMillis = 800, easing = LinearOutSlowInEasing)),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "☠️",
                style = TextStyle(fontSize = 100.sp),
                modifier = Modifier.clickable { viewModel.reset() }
            )
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            if (!viewModel.isInitialized) {
                viewModel.init(size.width, size.height)
            }
            val path = Path()
            with(snake.points.first()) { path.moveTo(x, y)}
            for (i in 1..snake.points.lastIndex) {
                with(snake.points[i]) {
                    path.lineTo(x, y)
                }
            }
            drawPath(
                path = path,
                color = Color.Green,
                style = Stroke(
                    width = snake.width,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
            apple?.let {
                drawApple(it.x, it.y, viewModel.appleRadius)
            }
            if (!dead) {
                viewModel.advance()
            }
        }
    }
}
