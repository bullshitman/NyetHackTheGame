package com.bignerdranch.nyethack

fun main() {

    val player = Player("Madrigal", 89, true, false)
    printPlayerStatus(player)
    player.castFireball()
}

private fun printPlayerStatus(
    player: Player
) {
    println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}

