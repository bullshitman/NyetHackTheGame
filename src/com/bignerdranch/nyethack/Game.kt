fun main(args: Array<String>) {
    val name = "Madrigal"
    val isBlessed = true
    var healthPoint = 100
    val isImmortal = false
    val auraColor = auraColor(isBlessed, healthPoint, isImmortal)
    val healthStatus = formatHealthStatus(healthPoint, isBlessed)
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
    castFireBall()
}

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoint: Int, isImmortal: Boolean) =
    if (isBlessed && healthPoint > 50 || isImmortal) "GREEN" else "NONE"

private fun formatHealthStatus(healthPoint: Int, isBlessed: Boolean) =
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
private fun castFireBall(numFireBalls: Int = 2) =
    println("A glass of Fireball springs into existence. (x$numFireBalls)")