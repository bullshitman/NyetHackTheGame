fun main(args: Array<String>) {
    val name = "Madrigal"
    val isBlessed = true
    var healthPoint = 100
    val isImmortal = false
    val auraVisible = isBlessed && healthPoint > 50 || isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"


    val healthStatus = when (healthPoint) {
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
    println("(Aura: $auraColor) " +
    "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}