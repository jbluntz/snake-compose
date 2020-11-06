package com.jbluntz.snake

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.jbluntz.snake.ui.SnakeTheme

@Composable
fun App() {
    SnakeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            SnakeGame()
        }
    }
}