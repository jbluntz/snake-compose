package com.jbluntz.snake

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun Snake() {
    val viewModel = remember { SnakeViewModel() }
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(1/4f).align(Alignment.TopCenter).tapGestureFilter { viewModel.turn(Direction.UP) }) {}
        Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(1/4f).align(Alignment.BottomCenter).tapGestureFilter { viewModel.turn(Direction.DOWN) }) {}
        Surface(modifier = Modifier.fillMaxHeight().fillMaxWidth(1/4f).align(Alignment.CenterStart).tapGestureFilter { viewModel.turn(Direction.LEFT) }) {}
        Surface(modifier = Modifier.fillMaxHeight().fillMaxWidth(1/4f).align(Alignment.CenterEnd).tapGestureFilter { viewModel.turn(Direction.RIGHT) }) {}

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            if (!viewModel.isInitialized) {
                viewModel.reset(size.width, size.height)
            }
            val path = Path()
            with(viewModel.snake.first()) { path.moveTo(x, y)}
            for (i in 1..viewModel.snake.lastIndex) {
                with(viewModel.snake[i]) {
                    path.lineTo(x, y)
                }
            }
            drawPath(
                path = path,
                color = Color.Green,
                style = Stroke(
                    width = viewModel.snakeWidth,
                    cap = StrokeCap.Round
                )
            )
            viewModel.advance()
        }
    }
}