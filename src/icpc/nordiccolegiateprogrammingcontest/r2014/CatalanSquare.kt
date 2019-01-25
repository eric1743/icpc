package icpc.nordiccolegiateprogrammingcontest.r2014

import java.math.BigInteger

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    var buff = BigInteger.ONE
    for (i in 1..n+1) {
//        buff *= BigInteger("${4 * i * i - 2 * i}")
//        buff /= BigInteger("${i * i + i}")
        buff *= (4 * i * i - 2 * i).toBigInt()
        buff /= (i * i + i).toBigInt()
    }
    println(buff)
}

fun Int.toBigInt(): BigInteger {
    return BigInteger(byteArrayOf((this shr 24).toByte(), (this shr 16).toByte(), (this shr 8).toByte(), this.toByte()))
}