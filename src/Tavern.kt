import kotlin.math.roundToInt
const val TAVERN_NAME = "Taernyl's Folly"
var numberGallons = 5.0
var playerGold = 10
var playerSilver = 10
fun main(args: Array<String>) {
    placeorder("shandy, Dragon's Breath, 5.91")
//    placeorder("elixir,Shirley's Temple,4.12")
}

fun perfomPurchase(price: Double){
    displayBalance()
    displayEstPintsNumber(0)
    val totalPurse = playerGold + (playerSilver/100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")
    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")
    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
    displayEstPintsNumber(1)
    displayEstPintsNumber(12)

}

fun displayEstPintsNumber(pintsNumber: Int) {
    val pintsAvailable = (numberGallons / 0.125) - (pintsNumber.toDouble())
    println("Estimated number of pints: ${pintsAvailable.toInt()}")
    numberGallons = (pintsAvailable * 0.125)
    println("$TAVERN_NAME has: $numberGallons gallons.")
}

fun displayBalance() {
    println("Player's purse balance: Gold $playerGold, Silver: $playerSilver")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")){
        when (it.value.toLowerCase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

private fun placeorder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')

    val message = "Madrigal buys a $name ($type) for $price"
    println(message)
    perfomPurchase(price.toDouble())
    var phrase = "DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE! $name"
    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
    phrase = if (name == "Dragon's Breath"){
        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name")}"
    }else{
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}