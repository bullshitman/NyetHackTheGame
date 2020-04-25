fun main(args: Array<String>) {
    val name = "Madrigal"
    val isBlessed = true
    var healthPoint = 100
    val isImmortal = false
    val auraColor = auraColor(isBlessed, healthPoint, isImmortal)
    val healthStatus = formatHealthStatus(healthPoint, isBlessed)
    val condition = castFireBall(50)
    printPlayerStatus(auraColor, isBlessed, name, healthStatus, condition)

}

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String,
    condition: String
) {
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})" +
    " Condition: $condition")
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
private fun castFireBall(numFireBalls: Int = 2): String {
    println("A glass of Fireball springs into existence. (x$numFireBalls)")
    return when (numFireBalls) {
        in 1..10 -> "Typsy."
        in 11..20 -> "Sloshed"
        in 21..30 -> "Soused"
        in 31..40 -> "Stewed"
        else -> "T0aSt3d"
    }
}