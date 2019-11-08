fun main(args: Array<String>) {
//    var beverage = readLine()?.let {
//        if (it.isNotBlank()){
//            it.capitalize()
//        }else{
//            "Buttered Ale"
//        }
//    }
//    var beverage = readLine()!!.capitalize()
//        beverage = null
    var beverage = readLine()
    beverage?.let {
        beverage = it.capitalize()
    }?: println("i can't do this - beverage was null!")
//    println(beverage)
    val beverageServed: String = beverage ?: "buttered Ale"
    println(beverageServed)
}