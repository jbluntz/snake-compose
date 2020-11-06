package com.jbluntz.snake.model

import kotlin.math.abs

class Snake(private val baseLength: Float, private val targetLength: Float, val points: List<Point>, private val direction: Direction) {

    constructor(baseLength: Float, start: Point, direction: Direction) : this(baseLength, baseLength, listOf(start, start.shiftY(-baseLength)), direction)

    val width: Float
        get() = baseLength / 5
    val speed: Float
        get() = baseLength / 30
    private val length: Float
        get() {
            return points.mapIndexed { index, point ->
                if (index == 0) {
                    0f
                } else {
                    val distance = point - points[index-1]
                    abs(distance.x + distance.y)
                }
            }.sum()
        }
    private val isGrowing: Boolean
        get() = length < targetLength


    fun turn(direction: Direction): Snake {
        if (direction == this.direction || direction.isOpposite(this.direction)) {
            return this
        } else {
            return copy(direction = direction, points = mutableListOf(this.points.first()).also { it.addAll(this.points) })
        }
    }

    private fun copy(direction: Direction = this.direction, targetLength: Float = this.targetLength, points: List<Point> = this.points): Snake {
        return Snake(this.baseLength, targetLength, points, direction)
    }

    fun move(): Snake {
        val newSnake = points.toMutableList()
        moveHead(newSnake)
        if (!isGrowing) {
            moveTail(newSnake)
        }
        return copy(points = newSnake)
    }

    private fun moveHead(newSnake: MutableList<Point>) {
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

    private fun moveTail(newSnake: MutableList<Point>) {
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

    fun grow(growth: Float): Snake {
        return copy(targetLength = this.targetLength + growth)
    }

    fun contains(point: Point): Boolean {
        for (i in 3..points.lastIndex) {
            val first = points[i-1]
            val second = points[i]
            if (first.x == second.x) {
                if (point.x == first.x && (first.y..second.y).contains(point.y))
                    return true
            } else {
                if (point.y == first.y && (first.x..second.x).contains(point.x))
                    return true
            }
        }
        return false
    }

}

