package com.bignerdranch.nyethack.extensions

fun <T> Iterable<T>.random(): T = this.shuffled().first()

fun String.frame(padding: Int, formatChar: String = "*"): String {
    val middle = formatChar.padEnd(padding)
        .plus(this)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}