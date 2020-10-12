package com.jbluntz.snake.model

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

fun Direction.isOpposite(direction: Direction): Boolean {
    return  (this == Direction.UP && direction == Direction.DOWN) ||
            (this == Direction.DOWN && direction == Direction.UP) ||
            (this == Direction.LEFT && direction == Direction.RIGHT) ||
            (this == Direction.RIGHT && direction == Direction.LEFT)
}