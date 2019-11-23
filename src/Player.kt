class Player {
    var name = "madrigal"
        get() = field.capitalize()
    private set(value) {field = value.trim()}
    var healthPoint = 89
    val isBlessed = true
    private val isImmortal = false

    private fun auraColor(isBlessed: Boolean, healthPoint: Int, isImmortal: Boolean): String {
        val auraVisible = isBlessed && healthPoint > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }


    private fun formatHealthStatus(healthPoint: Int, isBlessed: Boolean) = when (healthPoint) {
        100 -> "is in good condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 ->
            if (isBlessed) {
                "has some minor wounds, but is healing quite quckly!"
            } else {
                "has some minor wounds."
            }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }

    fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")
}