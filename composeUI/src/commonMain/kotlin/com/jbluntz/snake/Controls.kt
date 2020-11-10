package com.jbluntz.snake

class Controls(
    var up: () -> Unit = {},
    var down: () -> Unit = {},
    var left: () -> Unit = {},
    var right: () -> Unit = {},
    var reset: () -> Unit = {},
)