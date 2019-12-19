package com.bignerdranch.nyethack

open class Room(val name:String){
    protected open val dangerLevel = 5
    fun description(): String {
        return "Room $name\nDanger level: $dangerLevel"
    }
    open fun load() = "Nothing much to see here..."
    open fun ringBell(count: Int) = "You can't ring the bell here."
}
class TownSquare: Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG"
    final override fun load() =
        "The villagers rally and cheer as you enter! \n${ringBell(1)}"

    override fun ringBell(count: Int) =
        (0 until count-1).forEach{
            println("You are rinning the bell!")
        }
}