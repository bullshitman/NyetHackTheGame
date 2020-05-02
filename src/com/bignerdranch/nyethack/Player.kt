import com.bignerdranch.nyethack.Coordinate
import java.io.File

class Player (_name: String,override var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean):Fightable {
    var name = "madrigal"
        get() = "${field.capitalize()} of ${homeTown.trimMargin()}"
        private set (value) {
            field = value.trim()
        }
    val homeTown by lazy { selectHomeTown() }
    var currentPosition = Coordinate(0, 0)

    override val diceCount: Int = 3

    override val diceSides: Int = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }

    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "Player must have a name." })
    }
    constructor(name: String) : this (name,
    isBlessed = true,
    isImmortal = false) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    fun auraColor() =
        if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"

    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds, but is healing quickly!"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt"
            else -> "is in awful condition!"
        }

    fun castFireball(numFireBalls: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireBalls)")

    private fun selectHomeTown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()
}