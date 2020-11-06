package com.jbluntz.snake

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.jbluntz.snake.model.Direction
import com.jbluntz.snake.model.Point
import com.jbluntz.snake.model.Snake

@Composable
fun SnakeGame() {
    val viewModel = remember { SnakeViewModel() }
    val snake: Snake by viewModel.snake.collectAsState()
    val apple: Point? by viewModel.apple.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(1/4f).align(Alignment.TopCenter).tapGestureFilter { viewModel.turn(Direction.UP) }) {}
        Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(1/4f).align(Alignment.BottomCenter).tapGestureFilter { viewModel.turn(Direction.DOWN) }) {}
        Surface(modifier = Modifier.fillMaxHeight().fillMaxWidth(1/4f).align(Alignment.CenterStart).tapGestureFilter { viewModel.turn(Direction.LEFT) }) {}
        Surface(modifier = Modifier.fillMaxHeight().fillMaxWidth(1/4f).align(Alignment.CenterEnd).tapGestureFilter { viewModel.turn(Direction.RIGHT) }) {}
        if (viewModel.dead) {
            Text(
                text = "☠",
                style = TextStyle(fontSize = 100.sp),
                modifier = Modifier.wrapContentSize().align(Alignment.Center).tapGestureFilter { viewModel.reset() }
            )
        }
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
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
                drawCircle(
                    color = Color.Red,
                    radius = viewModel.appleRadius,
                    center = Offset(it.x, it.y)
                )
            }
            if (!viewModel.dead) {
                viewModel.advance()
            }
        }
    }
}