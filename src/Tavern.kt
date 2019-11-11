import kotlin.math.roundToInt
const val TAVERN_NAME = "Taernyl's Folly"
var numberGallons = 5.0
var playerGold = 10
var playerSilver = 10
var playerDragonCoin = 5
fun main(args: Array<String>) {
    placeorder("shandy, Dragon's Breath, 5.91")
//    placeorder("elixir,Shirley's Temple,4.12")
}

fun perfomPurchase(price: Double){
    displayBalance()
    displayEstPintsNumber(0)
    val totalPurse = playerGold + (playerSilver/100.0)
    val totalDracPurse = playerDragonCoin * 1.43
    println("Total purse: $totalPurse")
    println("Total purse drac:  ${"%.2f".format(totalDracPurse)}")
    println("Purchasing item for $price")
    var remainingBalance: Double
    if (balanceCheckout(price)) {
        remainingBalance = totalPurse - price
        displayEstPintsNumber(1)
    } else {
        remainingBalance = totalPurse
        println("Madrigal has no money for deal.")
    }
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")
    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver

    if (dracBalanceCheckout(price)) {
        remainingBalance = totalDracPurse - price
        displayEstPintsNumber(1)
    } else {
        remainingBalance = totalDracPurse
        println("Madrigal has no money for deal.")
    }
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")
    playerDragonCoin = remainingBalance.roundToInt()
    displayBalance()

}

fun dracBalanceCheckout(price: Double): Boolean {
    return (playerDragonCoin * 1.43 - price) > 0
}

fun balanceCheckout(price: Double):Boolean {
    val totalPurse = playerGold + (playerSilver/100.0)
    return (totalPurse - price) > 0
}

fun displayEstPintsNumber(pintsNumber: Int) {
    val pintsAvailable = (numberGallons / 0.125) - (pintsNumber.toDouble())
    println("Estimated number of pints: ${pintsAvailable.toInt()}")
    numberGallons = (pintsAvailable * 0.125)
    println("$TAVERN_NAME has: $numberGallons gallons.")
}

fun displayBalance() {
    println("Player's purse balance: Gold $playerGold, Silver: $playerSilver, DracCoin: $playerDragonCoin")
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
