package icpc.nordiccolegiateprogrammingcontest.r2014

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (capacity,n) = br.readLine().split(" ").map { it.toInt() }
    var curpass = 0L
    var possible = true
    for(i in 0 until n){
        val(leave, join, stay) = br.readLine().split(" ").map { it.toInt() }
        curpass += join - leave
        possible = leave + join <= curpass + leave && curpass <= capacity && !(stay > 0 && curpass == capacity.toLong())
        if (!possible) break
    }
    if (possible && curpass == 0L) println("possible")
    else println("impossible")
}