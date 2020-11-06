package com.jbluntz.snake.model

data class Point(val x: Float, val y: Float) {

    operator fun minus(other: Point) = Point(x - other.x, y - other.y)

    fun shiftY(distance: Float) = Point(x, y + distance)
    fun shiftX(distance: Float) = Point(x + distance, y)
}