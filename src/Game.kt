fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoint = 89
    val isBlessed = true
    val isImmortal = false

    //Aura checking
    val auraColor = auraColor(isBlessed, healthPoint, isImmortal)
    val healthStatus = formatHealthStatus(healthPoint, isBlessed)
    //Player status
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
    castFireball()
}

private fun castFireball(numFireballs: Int = 2) =
    println("A glass of Fireball springs into existence. (x$numFireballs) Inebriation level: "
                + " ${if (numFireballs in 1..50) "${(1..50).random()}" else "50"} "
                + "Inebriation status: " + " ${when(numFireballs){
                    in 1..10 -> "tipsy"
                    in 11..20 -> "sloshed"
                    in 21..30 -> "soused"
                    in 31..40 -> "stewed"
                    in 41..50 -> "toSt3d"
                    else -> "NONE" }}")

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println("(Aura: $auraColor) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoint: Int, isImmortal: Boolean) =
    if (isBlessed && healthPoint > 50 || isImmortal) "GREEN" else "NONE"


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