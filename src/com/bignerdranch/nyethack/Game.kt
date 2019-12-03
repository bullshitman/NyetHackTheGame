package com.bignerdranch.nyethack

fun main() {

    Game.play()
}
object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()
    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }
    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())
            //condition
            printPlayerStatus(player)
            print("> Enter your command: ")
            println("Last command: ${readLine()}")
        }
    }
    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }
}

