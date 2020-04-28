import java.io.File
import java.lang.StringBuilder
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniqPatrons = mutableSetOf<String>()
val menuList = File("data/tavern_menu_data.txt").readText().split("\n")
val setsOfDrinks = mutableSetOf<String>()
val listWidth = 40
fun main(args: Array<String>) {
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniqPatrons += name
    }
    println(uniqPatrons)
    val welcomeString = "Welcome to $TAVERN_NAME"
    val stars = prepareOutString("*", (listWidth - welcomeString.length - 2) / 2)
    println("$stars $welcomeString $stars")
//    menuList.forEach { data ->
//        val (_, name, price) = data.split(',')
//        val words = name.split(" ").joinToString { it.capitalize()}.trimEnd()
//        val menuLine = "$words${prepareOutString(".", listWidth - words.length - price.trimMargin().length)}${price.trimMargin()}"
//        println(menuLine)
//    }
    menuList.forEach { data ->
        val (type, _, _) = data.split(',')
        setsOfDrinks += type
    }
    setsOfDrinks.forEach { cathegory ->
        val cathegotyString = prepareOutString(" ", (listWidth - cathegory.length - 4)/2)
        println("$cathegotyString~[$cathegory]~$cathegotyString")
        menuList.forEach { data ->
            val (type, name, price) = data.split(',')
            if (cathegory == type) {
                val words = name.split(" ").joinToString { it.capitalize()}.trimEnd()
                val menuString = "$words${prepareOutString(".", listWidth - words.length - price.trimMargin().length)}${price.trimMargin()}"
                println(menuString)
            }
        }
    }
    println("$stars $welcomeString $stars")
    menuList.forEach { data ->
        val (_, name, price) = data.split(',')

        val words = name.split(" ").joinToString { it.capitalize()}.trimEnd()

        val menuString = "$words${prepareOutString(".", listWidth - words.length - price.trimMargin().length)}${price.trimMargin()}"
        println(menuString)
    }

    var orderCount = 0
//    while (orderCount <= 9) {
//        placeOrder(uniqPatrons.shuffled().first(), menuList.shuffled().first())
//        orderCount++
//    }
}

private fun prepareOutString(symbol: String, count: Int): String {
    var newLine = StringBuilder()
    repeat(count) {
        newLine.append(symbol)
    }
    return newLine.toString()
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for ${price.trimMargin()}."
    println(message)
//    preformPurchase(price.toDouble())
//    val phrase = "Ah, delicious $name"
//    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("Ah, delicious $name")}"
    } else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

private fun preformPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver/100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")
        val remainingBalance = totalPurse - price
        println("remaining balance: ${"%.2f".format(remainingBalance)}")
        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
        displayBalance()
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
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