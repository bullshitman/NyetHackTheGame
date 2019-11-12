import kotlin.math.roundToInt
import java.io.File
const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val menuList = File("data/tavern_menu_data.txt").readText().split("\n")
val uniquePatrons = mutableSetOf<String>()
fun main(args: Array<String>) {

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = patronList.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)
    var orderCount = 0
    var isTavernOpen = true
    val isClosingTime = false
    while (orderCount <= 9){
        placeorder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }
    while (isTavernOpen == true){
        if (isClosingTime){
            break
        }
        println("Having a grand old time!")
    }

//    placeorder("shandy, Dragon's Breath, 5.91")
//    placeorder("elixir,Shirley's Temple,4.12")
}

fun perfomPurchase(price: Double){
    displayBalance()
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

private fun placeorder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    println(message)
//    perfomPurchase(price.toDouble())
    var phrase = "DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE! $name"
//    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
    phrase = if (name == "Dragon's Breath"){
        "$patronName exclaims ${toDragonSpeak("Ah, delicious $name")}"
    }else{
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}
