package com.jbluntz.snake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.Direction
import kotlin.math.abs

class SnakeViewModel {

    private var xSize = 0f
    private var ySize = 0f
    private val speed
        get() = minOf(xSize, ySize) / 150
    private val startingLength
        get() = minOf(xSize, ySize) / 5

    val snakeWidth
        get() = startingLength / 10

    var isInitialized = false
        private set

    var snake by mutableStateOf(listOf(Offset(0f, 0f), Offset(0f,0f)))
        private set

    var direction by mutableStateOf(Direction.DOWN)
        private set
    private var turning = false

    fun reset(xSize: Float, ySize: Float) {
        this.xSize = xSize
        this.ySize = ySize
        snake = listOf(
            Offset(xSize/2, startingLength),
            Offset(xSize/2, 0f)
        )
        isInitialized = true
    }

    fun advance() {
        if (!isInitialized) { return }
        val newSnake =
            if (turning) {
                mutableListOf(snake.first()).apply {
                    addAll(snake)
                    turning = false
                }
            } else {
                snake.toMutableList()
            }

        moveHead(newSnake)
        moveTail(newSnake)

        snake = newSnake
    }

    private fun moveHead(newSnake: MutableList<Offset>) {
        val firstPoint = newSnake.first()
        when (direction) {
            Direction.DOWN -> {
                newSnake[0] = firstPoint.shiftY(speed)
            }
            Direction.UP -> {
                newSnake[0] = firstPoint.shiftY(-speed)
            }
            Direction.LEFT -> {
                newSnake[0] = firstPoint.shiftX(-speed)
            }
            Direction.RIGHT -> {
                newSnake[0] = firstPoint.shiftX(speed)
            }
        }
    }

    private fun moveTail(newSnake: MutableList<Offset>) {
        val nextToLastPoint = newSnake[newSnake.lastIndex - 1]
        val lastPoint = newSnake.last()

        val distance = lastPoint - nextToLastPoint
        if (distance.x == 0f) {
            if (abs(distance.y) < speed) {
                newSnake.removeLast()
            } else {
                newSnake[newSnake.lastIndex] =
                    lastPoint.shiftY(if (distance.y > 0) -speed else speed)
            }
        } else if (distance.y == 0f) {
            if (abs(distance.x) < speed) {
                newSnake.removeLast()
            } else {
                newSnake[newSnake.lastIndex] =
                    lastPoint.shiftX(if (distance.x > 0) -speed else speed)
            }
        }
    }

    fun turn(direction: Direction) {
        if (direction.isOpposite(this.direction)) { return }
        this.direction = direction
        turning = true
    }
}

private fun Direction.isOpposite(direction: Direction): Boolean {
    return  (this == Direction.UP && direction == Direction.DOWN) ||
            (this == Direction.DOWN && direction == Direction.UP) ||
            (this == Direction.LEFT && direction == Direction.RIGHT) ||
            (this == Direction.RIGHT && direction == Direction.LEFT)
}

fun Offset.shiftX(f: Float): Offset {
    return this.copy(x = this.x + f)
}
fun Offset.shiftY(f: Float): Offset {
    return this.copy(y = this.y + f)
}
