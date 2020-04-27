import java.lang.Exception
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    var swordJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient){
        swordJuggling = 2
    }
    try {
        proficientCheck(swordJuggling)
        swordJuggling = swordJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("You juggle $swordJuggling swords!")
}

fun proficientCheck(swordJuggling: Int?) {
    checkNotNull(swordJuggling, {"Player cannot juggle swords."})
}
class UnskilledSwordJugglerException():
        IllegalStateException("Player cannot juggle swords.")
