const val TAVERN_NAME = "Taernyl's Folly"
fun main(args: Array<String>) {
    placeOrder("Shandy,Dragon's Breath,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)
//    val phrase = "Ah, delicious $name"
//    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name")}"
    } else {
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}

private fun toDragonSpeak(phrase: String) =
    phrase.toUpperCase().replace(Regex("[AEIOU]")){
        when (it.value) {
            "A" -> "4"
            "E" -> "3"
            "I" -> "1"
            "O" -> "0"
            "U" -> "|_|"
            else -> it.value
        }
    }