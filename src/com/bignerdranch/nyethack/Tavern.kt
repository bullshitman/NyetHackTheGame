import java.io.File
import com.bignerdranch.nyethack.extensions.random

const val TAVERN_NAME = "Taernyl's Folly"
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniqPatrons = mutableSetOf<String>()
val menuList = File("data/tavern_menu_data.txt").readText().split("\n")
val patronGold = mutableMapOf<String, Double>()
fun main(args: Array<String>) {
    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniqPatrons += name
    }
    println(uniqPatrons)
    uniqPatrons.forEach{
        patronGold[it] = 6.0
    }
    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniqPatrons.random(), menuList.random())
        orderCount++
    }
    displayPatronBalance()
}

private fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price

}

private fun displayPatronBalance() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for ${price.trimMargin()}."
    println(message)
    performPurchase(price.toDouble(), patronName)
//    val phrase = "Ah, delicious $name"
//    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("Ah, delicious $name")}"
    } else {
        "$patronName says: Thanks for the $name"
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