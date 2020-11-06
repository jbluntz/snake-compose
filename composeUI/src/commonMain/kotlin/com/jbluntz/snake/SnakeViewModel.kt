package com.jbluntz.snake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jbluntz.snake.model.Direction
import com.jbluntz.snake.model.Point
import com.jbluntz.snake.model.Snake

class SnakeViewModel {

    private var xSize = 0f
    private var ySize = 0f
    private val startingLength
        get() = minOf(xSize, ySize) / 5

    var snake by mutableStateOf(Snake(0f, Point(0f, 0f), Direction.DOWN))
        private set

    var apple by mutableStateOf<Point?>(null)
        private set
    private var appleTimer = 0
    val appleRadius
        get() = snake.width

    var isInitialized = false
        private set

    val dead: Boolean
        get() = snake.points.first().let {
            !(0f..xSize).contains(it.x) ||
            !(0f..ySize).contains(it.y) ||
             snake.contains(it)
        }

    fun reset() {
        isInitialized = false
        Snake(0f, Point(0f, 0f), Direction.DOWN)
        apple = null
    }

    fun init(xSize: Float, ySize: Float) {
        this.xSize = xSize
        this.ySize = ySize
        snake = Snake(startingLength, Point(xSize/2, startingLength), Direction.DOWN)
        isInitialized = true
    }

    fun advance() {
        if (!isInitialized) { return }
        snake = snake.move()
        maybeAddApple()
        maybeEatApple()
    }

    fun turn(direction: Direction) {
        snake = snake.turn(direction)
    }

    //region apple
    private fun maybeAddApple() {
        if (apple != null) { return }
        if (appleTimer > 0 ) {
            appleTimer -= 1
            return
        }
        val interval = snake.speed
        val x = (1..(xSize/interval).toInt()).random()
        val y = (1..(ySize/interval).toInt()).random()
        apple = Point(x*interval, y*interval)
        appleTimer = 200
    }

    private fun maybeEatApple() {
        apple?.let {
            val snakeHead = snake.points.first()
            if (
                ((it.x - appleRadius)..(it.x + appleRadius)).contains(snakeHead.x) &&
                ((it.y - appleRadius)..(it.y + appleRadius)).contains(snakeHead.y)
            ) {
                apple = null
                snake = snake.grow(snake.speed * 10)
            }
        }
    }

    //endregion
}