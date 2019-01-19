package icpc.nordiccolegiateprogrammingcontest.r2014

import java.math.BigInteger

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val cList = mutableListOf(BigInteger.ONE, BigInteger.ONE, BigInteger("2"))
    var buff = BigInteger("2")
    for(i in 3 .. n) {
        buff *= BigInteger("${4*i*i - 2*i}");
        buff /= BigInteger("${i*i + i}");
        cList += buff
    }
    buff = BigInteger.ZERO
    for(i in 0..n){
        buff += cList[i] * cList[n-i]
    }
    println(buff)
}
