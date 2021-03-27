package com.jbluntz.snake

import com.jbluntz.snake.model.Direction
import com.jbluntz.snake.model.Point
import com.jbluntz.snake.model.Snake
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

private val initialSnake = Snake(0f, Point(0f, 0f), Direction.DOWN)

class SnakeViewModel {

    private var xSize = 0f
    private var ySize = 0f
    private val startingLength
        get() = minOf(xSize, ySize) / 5

    private val snakeFlow = MutableStateFlow(initialSnake)
    private var _snake by snakeFlow::value
    val snake: StateFlow<Snake> = snakeFlow

    private val appleFlow = MutableStateFlow<Point?>(null)
    private var _apple by appleFlow::value
    val apple: StateFlow<Point?> = appleFlow
    private var appleTimer = 0
    val appleRadius
        get() = snakeFlow.value.width

    var isInitialized = false
        private set

    val dead: Flow<Boolean> = snake.map { snake ->
        snake.points.first().let {
            !(0f..xSize).contains(it.x) ||
            !(0f..ySize).contains(it.y) ||
            snake.contains(it)
        }
    }

    fun reset() {
        isInitialized = false
        initialSnake
        _apple = null
    }

    fun init(xSize: Float, ySize: Float) {
        this.xSize = xSize
        this.ySize = ySize
        _snake = Snake(startingLength, Point(xSize/2, startingLength), Direction.DOWN)
        isInitialized = true
    }

    fun advance() {
        if (!isInitialized) { return }
        _snake = _snake.move()
        maybeAddApple()
        maybeEatApple()
    }

    fun turn(direction: Direction) {
        _snake = _snake.turn(direction)
    }

    //region apple
    private fun maybeAddApple() {
        if (_apple != null) { return }
        if (appleTimer > 0 ) {
            appleTimer -= 1
            return
        }
        val interval = _snake.speed
        val x = (1..(xSize/interval).toInt()).random()
        val y = (1..(ySize/interval).toInt()).random()
        _apple = Point(x*interval, y*interval)
        appleTimer = 200
    }

    private fun maybeEatApple() {
        _apple?.let {
            val snakeHead = _snake.points.first()
            if (
                ((it.x - appleRadius)..(it.x + appleRadius)).contains(snakeHead.x) &&
                ((it.y - appleRadius)..(it.y + appleRadius)).contains(snakeHead.y)
            ) {
                _apple = null
                _snake = _snake.grow(_snake.speed * 10)
            }
        }
    }

    //endregion
}