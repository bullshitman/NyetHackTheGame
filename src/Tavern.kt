import java.io.File
const val TAVERN_NAME = "Taernyl's Folly"
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val menuList = File("data/tavern_menu_data.txt").readText().split("\n")
val uniquePatrons = mutableSetOf<String>()
val patronGold = mutableMapOf<String, Double>()
fun main(args: Array<String>) {
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = patronList.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }
    var orderCount = 0
    while (orderCount <= 9){
        placeorder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }
    println(patronGold)
    displayPatronBalances()
}

fun displayPatronBalances() {
    patronGold.forEach { (patron, balance) ->
        println("$patron, balance:  ${"%.2f".format(balance)}")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
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
    var message = ""
    if (checkPatronsBalance(patronName, price.toDouble())) {
        message = "$patronName buys a $name ($type) for $price"
        performPurchase(price.toDouble(), patronName)
        var phrase = "DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE! $name"
        phrase = if (name == "Dragon's Breath"){
            "$patronName exclaims ${toDragonSpeak("Ah, delicious $name")}"
        }else{
            "$patronName says: Thanks for the $name"
        }
        println(phrase)
    }else{
        message = "$patronName has no money to buy a $name ($type) for $price"
        uniquePatrons.remove(patronName)
    }
    println(message)

}

fun checkPatronsBalance(patronName: String, price: Double):Boolean {
    val totalPurse = patronGold.getValue(patronName)
    return (totalPurse - price) > 0
}
