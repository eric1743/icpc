import java.math.BigInteger
import java.util.*


fun main(args: Array<String>) {
    val j = BigInteger("255")
    val b = (1000 shr 32).toByte()
    val i = j.toByteArray()
    val k = BigInteger(i)
    println(i)
}


