class Player {
    var name = "madrigal"
        get() = field.capitalize()
        private set (value) {
            field = value.trim()
        }
    val isBlessed = true
    var healthPoint = 89
    private val isImmortal = false

    fun auraColor() =
        if (isBlessed && healthPoint > 50 || isImmortal) "GREEN" else "NONE"

    fun formatHealthStatus() =
        when (healthPoint) {
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
}