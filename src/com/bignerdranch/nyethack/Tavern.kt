import java.math.RoundingMode
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10
var playerDracCoins = 5.0
var gallons = 5.0
var pintsCount = gallons / 0.125
fun main(args: Array<String>) {
    placeOrder("Shandy,Dragon's Breath,5.91")

}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)
    preformPurchase(price.toDouble())
//    val phrase = "Ah, delicious $name"
//    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name")}"
    } else {
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}

private fun preformPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver/100.0)
    val totalPurseCoins = playerDracCoins * 1.43
    println("Total purse: $totalPurse")
    println("Total purse: ${"%.4f".format(totalPurseCoins)}")
    println("Purchasing item for $price")
    if (checkPlayerBalance(price, totalPurse)) {
        val remainingBalance = totalPurse - price
        pintsCount -= 1
        println("remaining balance: ${"%.2f".format(remainingBalance)}")
        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
        displayBalance()
    } else {
        println("Not enough money.")
    }

    if (checkPlayerBalance(price, totalPurseCoins)) {
        val remainingBalance = totalPurseCoins - price
        pintsCount -= 1
        println("remaining balance: ${"%.2f".format(remainingBalance)}")
        val remainingCoins = remainingBalance / 1.43

        playerDracCoins = remainingCoins
        displayBalance()
    } else {
        println("Not enough money.")
    }
}

private fun checkPlayerBalance(price: Double, totalPurse: Double): Boolean {
    return (totalPurse - price) > 0
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
    println("Still have $pintsCount of Dragon's Breath.")
    println("Player's purse balance: coins: ${"%.4f".format(playerDracCoins)}\"")
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