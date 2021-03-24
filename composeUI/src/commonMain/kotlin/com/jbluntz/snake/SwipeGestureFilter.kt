package com.jbluntz.snake

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jbluntz.snake.model.Direction

@Composable
fun Modifier.swipeable(handler: (Direction) -> Unit): Modifier {
    return this.draggable(
        orientation = Orientation.Horizontal,
        state = rememberDraggableState {
            if (it > 0) {
                handler(Direction.RIGHT)
            } else {
                handler(Direction.LEFT)
            }
        }
    ).draggable(
        orientation = Orientation.Vertical,
        state = rememberDraggableState {
            if (it > 0) {
                handler(Direction.DOWN)
            } else {
                handler(Direction.UP)
            }
        }
    )
}