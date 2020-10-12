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
        get() = startingLength / 5

    var snake by mutableStateOf(listOf(Offset(0f, 0f), Offset(0f,0f)))
        private set

    var apple by mutableStateOf<Offset?>(null)
        private set
    private var appleTimer = 0
    val appleRadius
        get() = snakeWidth

    private var growTimer = 0

    var direction by mutableStateOf(Direction.DOWN)
        private set
    private var turning = false

    var isInitialized = false
        private set

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
        moveSnake()
        maybeAddApple()
        maybeEatApple()
    }

    //region movement
    private fun moveSnake() {
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
        if (growTimer > 0) {
            growTimer -= 1
        } else {
            moveTail(newSnake)
        }

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
    //endregion

    //region apple
    private fun maybeAddApple() {
        if (apple != null) { return }
        if (appleTimer > 0 ) {
            appleTimer -= 1
            return
        }
        val x = (1..(xSize/speed).toInt()).random()
        val y = (1..(ySize/speed).toInt()).random()
        apple = Offset(x*speed, y*speed)
        appleTimer = 200
    }

    private fun maybeEatApple() {
        apple?.let {
            val snakeHead = snake.first()
            if (it.x - appleRadius < snakeHead.x && snakeHead.x < it.x + appleRadius &&
                    it.y - appleRadius < snakeHead.y && snakeHead.y < it.y + appleRadius) {
                apple = null
                growTimer = 10
            }
        }
    }

    //endregion
}

private fun Direction.isOpposite(direction: Direction): Boolean {
    return  (this == Direction.UP && direction == Direction.DOWN) ||
            (this == Direction.DOWN && direction == Direction.UP) ||
            (this == Direction.LEFT && direction == Direction.RIGHT) ||
            (this == Direction.RIGHT && direction == Direction.LEFT)
}

private fun Offset.shiftX(f: Float): Offset {
    return this.copy(x = this.x + f)
}
private fun Offset.shiftY(f: Float): Offset {
    return this.copy(y = this.y + f)
}
