package com.jbluntz.snake

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.jbluntz.snake.model.Direction
import com.jbluntz.snake.ui.SnakeTheme

@Composable
fun App(controls: Controls = Controls()) {
    val viewModel = remember { SnakeViewModel() }
    controls.up = { viewModel.turn(Direction.UP)}
    controls.down = { viewModel.turn(Direction.DOWN)}
    controls.left = { viewModel.turn(Direction.LEFT)}
    controls.right = { viewModel.turn(Direction.RIGHT)}
    controls.reset = { viewModel.reset() }
    SnakeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            SnakeGame(viewModel)
        }
    }
}