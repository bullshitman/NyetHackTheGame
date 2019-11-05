fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoint = 89
    val isBlessed = true
    val isImmortal = false
    //Aura checking
    val auraVisible = isBlessed && healthPoint > 50 || isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"
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
    println("(Aura: $auraColor) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}