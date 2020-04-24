import kotlin.math.pow

fun main(args: Array<String>) {
    val name = "Madrigal"
    val isBlessed = true
    var healthPoint = 100
    val isImmortal = false
    val statusFormatString = "(HP)(A)(B) -> H"
    val auraVisible = isBlessed && healthPoint > 50 || isImmortal
    val auraColor = when ((Math.random().pow((110 - healthPoint) / 100.0) * 20 ).toInt()) {
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        in 16..20 -> "GREEN"
        else -> ""
    }
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
    println(statusFormatString.replace("HP", "Points: $healthPoint")
                              .replace("A", "Aura: ${if (auraVisible) auraColor else "NONE"}")
                              .replace("B", "Blessed: ${if (isBlessed) "YES" else "NO"}")
                              .replace("H", "$name $healthStatus"))
}