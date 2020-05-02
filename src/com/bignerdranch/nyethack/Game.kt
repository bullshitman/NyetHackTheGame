import com.bignerdranch.nyethack.Direction

fun main(args: Array<String>) {
    Game.play()
}


object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("long Corridor"), Room("Generic Room")))

    init {
        println("Welcome, adventure.")
        player.castFireball()
    }
    fun play() {
        var result : Any
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus(player)
            print("> Enter your command: ")
            result = GameInput(readLine()).processCommand()
            if (result !is String) break else println(result)
        }
    }
    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }
    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "quit" -> quitCommand()
            "map"  -> printMap()
            "ring" -> ringTheBell()
            else -> commandNotFound()
        }
        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
        private fun quitCommand(): Boolean {
            println("Good buy.")
            return false
        }
        private fun printMap() : String {
            for (listLine in worldMap) {
                for(element in listLine) {
                    print(if (element == currentRoom) " X " else " O ")
                }
                print("\n")
            }
            return "You're here - X"
        }
    }
    private fun ringTheBell () =
        if (currentRoom is TownSquare) {
            (currentRoom as TownSquare).ringBell()
        } else {
            " You're not on townsquare."
        }
    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBound) {
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "Ok, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput."
        }
}
