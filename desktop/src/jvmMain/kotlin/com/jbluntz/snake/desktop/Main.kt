package com.jbluntz.snake.desktop

import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.ui.input.key.ExperimentalKeyInput
import androidx.compose.ui.input.key.Key
import com.jbluntz.snake.App
import com.jbluntz.snake.Controls

@OptIn(ExperimentalKeyInput::class)
fun main() = Window {
    val controls = Controls()
    App(controls)
    AppWindowAmbient.current?.keyboard?.let {
        it.setShortcut(Key.W, controls.up)
        it.setShortcut(Key.A, controls.left)
        it.setShortcut(Key.S, controls.down)
        it.setShortcut(Key.D, controls.right)
        it.setShortcut(Key.Spacebar, controls.reset)
    }
}