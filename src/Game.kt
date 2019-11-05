fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoint = 89
    val isBlessed = true
    val isImmortal = false
    //Aura checking
    val auraVisible = isBlessed && healthPoint > 50 || isImmortal
    val karma = (Math.pow(Math.random(), (110-healthPoint)/100.0)*20).toInt()
    val auraColor = when (karma){
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        in 16..20 -> "GREEN"
        else -> ""
    }

    val healthStatus = when (healthPoint){
        100 -> "is in good condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 ->
            if (isBlessed){
                "has some minor wounds, but is healing quite quckly!"
            }else{
                "has some minor wounds."
            }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }
    //Player status
    println("(Aura: ${if (auraVisible) auraColor else "NONE"}) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}