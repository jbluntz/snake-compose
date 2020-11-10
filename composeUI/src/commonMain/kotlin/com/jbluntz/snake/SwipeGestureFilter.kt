package com.jbluntz.snake

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import kotlin.math.abs

fun Modifier.swipeGestureFilter(handler: (Direction) -> Unit): Modifier {
    return this.dragGestureFilter(dragObserver = object : DragObserver {
        override fun onStop(velocity: Offset) {
            if (abs(velocity.x) > abs(velocity.y)) {
                if (velocity.x > 0) {
                    handler(Direction.RIGHT)
                } else {
                    handler(Direction.LEFT)
                }
            } else {
                if (velocity.y > 0) {
                    handler(Direction.DOWN)
                } else {
                    handler(Direction.UP)
                }
            }
        }
    })
}